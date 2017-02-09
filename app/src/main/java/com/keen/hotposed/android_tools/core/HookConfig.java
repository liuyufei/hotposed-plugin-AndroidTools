package com.keen.hotposed.android_tools.core;

import android.os.Environment;


import com.keen.hotposed.android_tools.hooks.IntentMonitor;

import java.io.File;
import java.util.ArrayList;

public class HookConfig {

    public static final String TAG = "AndroidMonitorPlugin.";

    public static final String LOGPATH = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "hotposed" +
            File.separator + "log" +
            File.separator + HookEntrance.packageName +
            File.separator;

    public static final ArrayList<Class<?>> hookList = new ArrayList<Class<?>>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            add(IntentMonitor.class);
        }
    };

}
