package com.example.a37_1.e_advertisement.model;

import io.realm.RealmObject;

/**
 * Created by User on 15.11.2017.
 */

public class News extends RealmObject {
    String title;
    String content;

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
