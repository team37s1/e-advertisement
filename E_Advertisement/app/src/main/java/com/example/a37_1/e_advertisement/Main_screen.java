package com.example.a37_1.e_advertisement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class Main_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TableRow news1;
        TableRow news2;
        TableRow news3;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        news1 = (TableRow) findViewById(R.id.news1);
        news1.setOnClickListener(buttonClickListener);

        news1 = (TableRow) findViewById(R.id.news2);
        news1.setOnClickListener(buttonClickListener);

        news1 = (TableRow) findViewById(R.id.news3);
        news1.setOnClickListener(buttonClickListener);
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(v.getContext(),DescriptionNewActivity.class);
            startActivityForResult(intent,0);

        }
    };

}


