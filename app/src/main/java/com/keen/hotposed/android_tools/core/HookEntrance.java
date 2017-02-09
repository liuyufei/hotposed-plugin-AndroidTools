package com.keen.hotposed.android_tools.core;

import com.keen.hotposed.support.HotposedInterface.IHotposedHook;
import com.keen.hotposed.support.HotposedInterface.IHotposedPlugin;

import java.io.File;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookEntrance implements IHotposedPlugin {

    public static ClassLoader classLoader;
    public static String packageName;

    @Override
    public void startHotposedPlugin(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        classLoader = loadPackageParam.classLoader;
        packageName = loadPackageParam.packageName;

        for(Class<?> clazz : HookConfig.hookList) {

            try {
                IHotposedHook hook = (IHotposedHook) clazz.newInstance();
                hook.doHook();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            File logDir = new File(HookConfig.LOGPATH);

            if(!logDir.exists()) {
                logDir.mkdirs();
            }

        }

    }
}
