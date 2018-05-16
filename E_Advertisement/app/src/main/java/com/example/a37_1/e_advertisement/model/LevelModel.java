package com.example.a37_1.e_advertisement.model;

/**
 * Created by Vova0199 on 07.05.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LevelModel {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("level")
    @Expose
    private String level;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LevelModel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}