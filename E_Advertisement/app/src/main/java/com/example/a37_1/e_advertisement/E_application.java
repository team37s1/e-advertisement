package com.example.a37_1.e_advertisement;


import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class E_application extends Application{
    public static final String AUTH_URL = "http://" + BuildConfig.OBJECT_SERVER_IP + ":9080/auth";
    public static final String REALM_URL = "realm://" + BuildConfig.OBJECT_SERVER_IP + ":9080/~/realmtasks";
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("37_1_db.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
