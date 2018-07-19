package com.example.administrator.im.util;

import android.util.Log;

/**
 * Created by Administrator on 2018/7/15.
 */

public class LogUtils {
    private static final String TAG = "IM";
    public static final boolean IS_DEBUG = true;

    public static void d(String message) {
        if (IS_DEBUG) Log.d(TAG, message);
    }
}