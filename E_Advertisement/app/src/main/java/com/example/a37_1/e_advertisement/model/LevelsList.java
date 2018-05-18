package com.example.a37_1.e_advertisement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Vova0199 on 08.05.2018.
 */

public class LevelsList {

    @SerializedName("levels")
    @Expose
    private ArrayList<LevelModel> levels = new ArrayList<>();

    public ArrayList<LevelModel> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<LevelModel> levels) {
        this.levels = levels;
    }

}