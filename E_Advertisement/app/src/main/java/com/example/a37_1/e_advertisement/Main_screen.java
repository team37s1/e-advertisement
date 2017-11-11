package com.example.a37_1.e_advertisement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableRow;

public class Main_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TableRow news1;
        FloatingActionButton addNews = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        addNews = (FloatingActionButton) findViewById(R.id.addNews);
        addNews.setOnClickListener(addnew);

        news1 = (TableRow) findViewById(R.id.news1);
        news1.setOnClickListener(viewContent);

        news1 = (TableRow) findViewById(R.id.news2);
        news1.setOnClickListener(viewContent);

        news1 = (TableRow) findViewById(R.id.news3);
        news1.setOnClickListener(viewContent);
    }

    View.OnClickListener viewContent = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(v.getContext(),DescriptionNewActivity.class);
            startActivityForResult(intent,0);

        }
    };
    View.OnClickListener addnew = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(v.getContext(),Admin_page.class);
            startActivityForResult(intent,0);

        }
    };


}


