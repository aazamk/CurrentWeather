package com.example.weatherapp.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.SyncStateContract;

public class Connection {

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager
                = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static boolean isTimePass(long lastStored) {
        return System.currentTimeMillis() - lastStored > Constant.TIME_TO_PASS;
    }
}
