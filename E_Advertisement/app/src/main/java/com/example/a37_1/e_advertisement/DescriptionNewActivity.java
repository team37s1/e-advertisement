package com.example.a37_1.e_advertisement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DescriptionNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        delete TITLE Bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_description_new);
    }
}
