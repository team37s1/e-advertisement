package com.example.a37_1.e_advertisement.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;



public class News extends RealmObject {
    private String title;
    private String content;
//    private String area;
//    private String category;

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
//                ",area='"+ area +'\''+
//                ",category'="+category +'\''+
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
//    public String getArea(String area) {
//        return area;
//    }
//
//    public void setArea(String area) {
//        this.area = area;
//    }
//
//    public String getCategory(String category) {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
}
