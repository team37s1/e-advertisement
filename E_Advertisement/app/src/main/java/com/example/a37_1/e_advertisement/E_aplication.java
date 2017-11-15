package com.example.a37_1.e_advertisement;


import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class E_aplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
