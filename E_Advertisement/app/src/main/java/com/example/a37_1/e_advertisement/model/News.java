package com.example.a37_1.e_advertisement.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;



public class News{
    public String title;
    public String content;
    public String area;
    public String category;
    public String  key;


    public News(){

    }
    public News(String title, String content, String area, String category, String key){
        this.title = title;
        this.content = content;
        this.area = area;
        this.category = category;
        this.key = key;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}