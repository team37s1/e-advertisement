package com.example.a37_1.e_advertisement.model;





public class News{
    public String title;
    public String content;
    public String area;
    public String category;
    public String  key;



    public News(){

    }
    public News(String area, String category, String content,  String title){

        this.area = area;
        this.category = category;
        this.content = content;
        this.title = title;
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

}