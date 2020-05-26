package com.example.pawan.androidtemplate.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class Utilities {
    public static boolean isOnline(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void showError(@Nullable Context context, String error) {
        if (context == null) return;
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

}
