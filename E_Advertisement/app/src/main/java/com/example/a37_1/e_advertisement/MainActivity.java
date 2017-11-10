package com.example.a37_1.e_advertisement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    go on DescriptionNewActivity

    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, DescriptionNewActivity.class);
        startActivity(intent);
    }
}
