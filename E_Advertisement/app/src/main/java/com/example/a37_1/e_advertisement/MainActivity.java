package com.example.a37_1.e_advertisement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

//    go on DescriptionNewActivity

    public void sendMessage(View view)
    {
        Intent intent = new Intent(MainActivity.this, Main_screen.class);
        startActivity(intent);
    }

}
