package com.celerysoft.ittakestwo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Celery on 16/1/29.
 * common util
 */
public class Util {
    /**
     * check the device if connecting to the internet
     * @param context Context
     * @return true if connecting to the internet
     */
    public static boolean isConnectToInternet(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
