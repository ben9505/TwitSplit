package com.example.ben.twitsplit;

import android.util.Log;

/**
 * Created by ben on 13/03/2018.
 */

public class Debug {
    private static final String TAG = "LTH";
    private static boolean ENABLE = true;

    public static void i(String log) {
        if (ENABLE) {
            System.out.println(log);
            Log.i(TAG,log);
        }
    }

}
