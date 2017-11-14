package com.example.a37_1.e_advertisement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class Admin_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_andmin_page);
            Spinner sArea = (Spinner) findViewById(R.id.sArea);
            Spinner sCategory = (Spinner) findViewById(R.id.sCategory);

            // Spinner click listener
            sArea.setOnItemSelectedListener(this);
            sCategory.setOnItemSelectedListener(this);

            List<String> area = new ArrayList<String>();
            area.add("Галицький");
            area.add("Франківський");
            area.add("Сихівський");
            area.add("Личаківський");
            area.add("Залізничний");
            area.add("Шевченківський");
            area.add("Виберіть район");

            List<String> category = new ArrayList<String>();
            category.add("Електроенергія");
            category.add("Газопостачання");
            category.add("Водопостачання");
            category.add("Рух громадського транспорту");
            category.add("Перекриття вулиць");
            category.add("Штормові попередження");
            category.add("Виберіть категорію");


            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, area);
            ArrayAdapter<String> dataCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            sArea.setAdapter(dataAdapter);
            sCategory.setAdapter(dataCategory);
            sArea.setSelection(6);
            sCategory.setSelection(6);
        }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}