package com.keen.hotposed.android_tools.util;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by liuyufei on 16/12/29.
 */
public class Util {

    public static void dumpKeySets(Bundle bundle, String TAG) {

        if (bundle == null) return;

        StringBuilder builder = new StringBuilder();

        builder.append("[Key Sets] : ");

        for (String s : bundle.keySet()) {
            builder.append(s + " | ");
        }

        Log.i(TAG, builder.toString());

    }

    public static void dumpBundle(Bundle bundle, String TAG) {

        if (bundle == null) return;

        dumpKeySets(bundle, TAG);

        for (String key : bundle.keySet()) {

            Object value = bundle.get(key);

            try {
                Log.i(TAG, key + " : " + value.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (value instanceof Bundle) {

                dumpKeySets((Bundle) value, TAG);

                for (String bundleKey : ((Bundle) value).keySet()) {
                    try {
                        Log.i(TAG, bundleKey + " : " + ((Bundle) value).get(bundleKey));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
