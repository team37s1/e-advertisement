package com.example.a37_1.e_advertisement;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TableRow news1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        news1 = (TableRow) findViewById(R.id.news1);
        news1.setOnClickListener(viewContent);

        news1 = (TableRow) findViewById(R.id.news2);
        news1.setOnClickListener(viewContent);

        news1 = (TableRow) findViewById(R.id.news3);
        news1.setOnClickListener(viewContent);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(MainActivity.this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    View.OnClickListener viewContent = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(v.getContext(),DescriptionNewActivity.class);
            startActivityForResult(intent,0);

        }
    };
}
