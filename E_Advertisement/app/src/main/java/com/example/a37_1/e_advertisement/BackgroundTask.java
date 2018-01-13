package com.example.a37_1.e_advertisement;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.a37_1.e_advertisement.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 13.01.2018.
 */

public class BackgroundTask {
    Context context;
    ArrayList<News> arrayList =  new ArrayList<>();
    String url = "http://192.168.0.101:8000/news";

    public BackgroundTask (Context context){
        this.context = context;
    }

    public ArrayList<News> getList(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count<response.length()){
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                News news = new News(jsonObject.getString("area"), jsonObject.getString("category"),
                                        jsonObject.getString("content"), jsonObject.getString("title"));
                                arrayList.add(news);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PIZDOS", String.valueOf(error));
            }
        });


        MySingleton.getmInstance(context).addToRequestque(jsonArrayRequest);
       return arrayList;
    }
}
