package com.example.a37_1.e_advertisement;

import io.realm.RealmObject;

/**
 * Created by User on 14.11.2017.
 */

public class SendNews extends RealmObject{

    private  String title;
    private  String context;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
