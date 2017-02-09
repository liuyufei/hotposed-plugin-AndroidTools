package com.keen.hotposed.android_tools.hooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.keen.hotposed.android_tools.core.HookConfig;
import com.keen.hotposed.android_tools.util.Util;
import com.keen.hotposed.support.HotposedInterface.IHotposedHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;


public class IntentMonitor implements IHotposedHook {

    public static final String TAG = HookConfig.TAG + "ActivityInvokeMonitor";

    XC_MethodHook hook = new XC_MethodHook() {

        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

            Log.i(TAG, "=============================================");

            Log.i(TAG, "[From]" + param.thisObject.getClass().getName());

            Log.i(TAG, "*********************************************");

            for (Object object : param.args) {

                if (object instanceof Intent) {
                    Util.dumpBundle(((Intent) object).getExtras(), TAG);
                }

                if (object instanceof Bundle) {
                    Util.dumpBundle((Bundle)object, TAG);
                }
            }
            Log.i(TAG, "*********************************************");

        }
    };

    XC_MethodHook onCreate_hook = new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

            Log.i(TAG, "[ To ]" + param.thisObject.getClass().getName());

            Intent intent = ((Activity)param.thisObject).getIntent();

            Log.i(TAG, "*********************************************");

            Util.dumpBundle(intent.getExtras(), TAG);

            Log.i(TAG, "*********************************************");


            Log.i(TAG, "=============================================");
            Log.i(TAG, "BLANK                                   BLANK");
            Log.i(TAG, "BLANK                                   BLANK");

        }
    };

    @Override
    public void doHook() {
        /*
         * These three methods are all redirected to the forth method
         */
//        XposedHelpers.findAndHookMethod(Activity.class, "startActivity", Intent.class, hook);
//        XposedHelpers.findAndHookMethod(Activity.class, "startActivity", Intent.class, Bundle.class, hook);
//        XposedHelpers.findAndHookMethod(Activity.class, "startActivityForResult", Intent.class, int.class, hook);
        XposedHelpers.findAndHookMethod(Activity.class, "startActivityForResult", Intent.class, int.class, Bundle.class, hook);

        XposedHelpers.findAndHookMethod(Activity.class, "onCreate", Bundle.class, onCreate_hook);
    }
}
