package com.example.a37_1.e_advertisement.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

/**
 * Created by Vova0199 on 07.05.2018.
 */

public class InternetConnection {

    public static boolean checkConnection(@NonNull Context context) {
    return ((ConnectivityManager) context.getSystemService
            (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
}
}