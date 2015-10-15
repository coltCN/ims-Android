package com.coltcn.ims.utils;

import com.coltcn.ims.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by majf on 2015/10/15.
 */
public class L {
    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    public static void i(String msg) {
        if (isDebuggable())
            Logger.i(msg);
    }

    public static void d(String msg) {
        if (isDebuggable())
            Logger.d(msg);
    }

    public static void e(String msg) {
        if (isDebuggable())
            Logger.e(msg);
    }


    public static void json(String json) {
        if (isDebuggable())
            Logger.json(json);
    }
}
