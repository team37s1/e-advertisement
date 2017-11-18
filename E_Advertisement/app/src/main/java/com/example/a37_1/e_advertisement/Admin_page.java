package com.example.a37_1.e_advertisement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a37_1.e_advertisement.model.News;
import java.util.ArrayList;
import java.util.List;

import io.realm.ErrorCode;
import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;

import static android.os.Build.ID;
import static android.provider.Telephony.Carriers.PASSWORD;
import static com.example.a37_1.e_advertisement.E_application.AUTH_URL;
import static com.example.a37_1.e_advertisement.E_application.REALM_URL;


public class Admin_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btnSend;
    Spinner sArea;
    Spinner sCategory;
    Realm realm;
    EditText title;
    EditText content;
    TextView txtView;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_andmin_page);
            sArea = (Spinner) findViewById(R.id.sArea);
            sCategory = (Spinner) findViewById(R.id.sCategory);
            realm = Realm.getDefaultInstance();
            title = findViewById(R.id.ettitle);
            content = findViewById(R.id.etContent);
            btnSend = findViewById(R.id.addNews);
            txtView = findViewById(R.id.textView);

            showNews();
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

            createUserIfNeededAndAndLogin();
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

            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    save_do_db(title.getText().toString().trim(), content.getText().toString().trim());

                }
            });
        }

    private void showNews() {

        String output = "";
        RealmResults<News> result = realm.where(News.class).findAllAsync();
        result.load();
        for (News news:result){
            output +=news.toString();
        }
        txtView.setText(output);
    }


    private void save_do_db(final String title, final String content) {
//        realm.beginTransaction(); // Persist unmanaged objects
//        News news = realm.createObject(News.class);
//        news.setTitle(title);
//        news.setContent(content);
//        realm.commitTransaction();
        realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm bgRealm) {

                News news = bgRealm.createObject(News.class);

                news.setTitle(title);
                news.setContent(content);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.v("Successes", "Vse norm");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("Ne och norm", error.getMessage());
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
        realm.close();
    }


    private void createUserIfNeededAndAndLogin() {
        final SyncCredentials syncCredentials = SyncCredentials.usernamePassword(ID, PASSWORD, false);

        // Assume user exist already first time. If that fails, create it.
        SyncUser.loginAsync(syncCredentials, AUTH_URL, new SyncUser.Callback<SyncUser>() {
            @Override
            public void onSuccess(SyncUser user) {
                final SyncConfiguration syncConfiguration = new SyncConfiguration.Builder(user, REALM_URL).build();
                Realm.setDefaultConfiguration(syncConfiguration);
                realm = Realm.getDefaultInstance();
            }

            @Override
            public void onError(ObjectServerError error) {
                if (error.getErrorCode() == ErrorCode.INVALID_CREDENTIALS) {
                    // User did not exist, create it
                    SyncUser.loginAsync(SyncCredentials.usernamePassword(ID, PASSWORD, true), AUTH_URL, this);
                } else {
                    String errorMsg = String.format("(%s) %s", error.getErrorCode(), error.getErrorMessage());
                    Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

