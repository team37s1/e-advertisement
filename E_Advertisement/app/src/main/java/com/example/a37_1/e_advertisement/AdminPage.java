package com.example.a37_1.e_advertisement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.volokh.danylo.hashtaghelper.HashTagHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdminPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnSend;
    Spinner sArea;
    Spinner sCategory;
    EditText title;
    EditText content;
    TextView txtView;
    HashTagHelper mTextHashTagHelper;
    TextView mHashTagText;

    String url = "http://192.168.0.101:8000/api/news";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andmin_page);
        sArea = findViewById(R.id.sArea);
        sCategory = findViewById(R.id.sCategory);
        title = findViewById(R.id.ettitle);
        content = findViewById(R.id.etContent);
        btnSend = findViewById(R.id.addNews);
        txtView = findViewById(R.id.textView);

        mHashTagText = findViewById(R.id.etContent);
        mTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimary), null);
        mTextHashTagHelper.handle(mHashTagText);

        // Spinner click listener
        sArea.setOnItemSelectedListener(this);
        sCategory.setOnItemSelectedListener(this);

        final List<String> area = new ArrayList<>();
        area.add("Галицький");
        area.add("Франківський");
        area.add("Сихівський");
        area.add("Личаківський");
        area.add("Залізничний");
        area.add("Шевченківський");
        area.add("Виберіть район");

        List<String> category = new ArrayList<>();
        category.add("Електроенергія");
        category.add("Газопостачання");
        category.add("Водопостачання");
        category.add("Перекриття вулиць");
        category.add("Стихійні попередження");
        category.add("Виберіть категорію");
        final int categorySize = category.size() - 1;
        final int areaSize = area.size() - 1;

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, area) {
            @Override
            public int getCount() {
                return (areaSize); // Truncate the list
            }
        };
        ArrayAdapter<String> dataCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category) {
            @Override
            public int getCount() {
                return (categorySize); // Truncate the list
            }
        };

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sArea.setAdapter(dataAdapter);
        sCategory.setAdapter(dataCategory);
        sArea.setSelection(areaSize);
        sCategory.setSelection(categorySize);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (title.getText().toString().trim().equals("") || content.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Заповніть всі поля", Toast.LENGTH_LONG).show();
                } else {
                    final String titleValue, areaValue, categoryValue, contentValue;

                    titleValue = title.getText().toString().trim();
                    areaValue = sArea.getSelectedItem().toString();
                    categoryValue = sCategory.getSelectedItem().toString();
                    contentValue = content.getText().toString().trim();

                    Map<String, String> params = new HashMap();
                    params.put("title", titleValue);
                    params.put("content", contentValue);
                    params.put("area", areaValue);
                    params.put("category", categoryValue);

                    JSONObject parameters = new JSONObject(params);
                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();

                        }
                    });
                    title.setText("");
                    content.setText("");
                    MySingleton.getmInstance(AdminPage.this).addToRequestque(jsonRequest);

                }
            }
        });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
