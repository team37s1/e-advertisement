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
import com.example.a37_1.e_advertisement.model.News;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.volokh.danylo.hashtaghelper.HashTagHelper;

import java.util.ArrayList;
import java.util.List;


public class AdminPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnSend;
    Spinner sArea;
    Spinner sCategory;
    EditText title;
    EditText content;
    TextView txtView;
    HashTagHelper mTextHashTagHelper;
    TextView mHashTagText;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myDb = database.getReference("news");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andmin_page);
        sArea =  findViewById(R.id.sArea);
        sCategory =  findViewById(R.id.sCategory);
        title = findViewById(R.id.ettitle);
        content = findViewById(R.id.etContent);
        btnSend = findViewById(R.id.addNews);
        txtView = findViewById(R.id.textView);

        mHashTagText = findViewById(R.id.etContent);
        mTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimary), null);
        mTextHashTagHelper.handle(mHashTagText);

        ArrayList<Object> mNews = new ArrayList<>();

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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, area){
            @Override
            public int getCount() {
                return(areaSize); // Truncate the list
            }
        };
        ArrayAdapter<String> dataCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category){
            @Override
            public int getCount() {
                return(categorySize); // Truncate the list
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

                if( title.getText().toString().trim().equals("") || content.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Заповніть всі поля", Toast.LENGTH_LONG).show();
                }
                else {
                    String titleValue = title.getText().toString().trim();
                    String areaValue = sArea.getSelectedItem().toString();
                    String categoryValue = sCategory.getSelectedItem().toString();
                    String contentValue = content.getText().toString().trim();

                    News news = new News();
                    news.setTitle(titleValue);
                    news.setContent(contentValue);
                    news.setArea(areaValue);
                    news.setCategory(categoryValue);

                    myDb.push().setValue(news);
                    title.setText("");
                    content.setText("");
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
