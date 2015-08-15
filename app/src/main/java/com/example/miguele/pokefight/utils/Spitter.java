package com.example.miguele.pokefight.utils;

import android.util.Log;

/**
 * Created by miguele on 8/14/15.
 */
public class Spitter {

    public static void Error(String TAG, String functionName, String error) {
        Log.i(TAG, "functionError("+ functionName + ")\n" + error);
    }

    public static void Log(String TAG, String functionName, String message) {
        Log.i(TAG, "functionLog(" +functionName +")\n" + message);
    }

}
