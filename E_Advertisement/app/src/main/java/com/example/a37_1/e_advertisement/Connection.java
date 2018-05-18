package com.example.a37_1.e_advertisement;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.a37_1.e_advertisement.model.News;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 13.01.2018.
 */

public class Connection {
    private Context context;
    private ArrayList<News> arrayList = new ArrayList<>();
    private String url;



    public Connection(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    Random rnd = new Random(System.currentTimeMillis());

    public int get_random() {
        int min = 2000;
        int max = 5000;
        int number = min + rnd.nextInt(max - min + 1);
        return number;
    }


    public  ArrayList<News> getList() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;


                        while (count < response.length()) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                News news = new News(jsonObject.getString("area"), jsonObject.getString("category"),
                                        jsonObject.getString("content"), jsonObject.getString("title"));
                                arrayList.add(news);
                                count++;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PIZDOS", String.valueOf(error));
            }

        });

        try{

            MySingleton.getmInstance(context).addToRequestque(jsonArrayRequest);
            Thread mainThread1 = Thread.currentThread();

            Log.d(mainThread1.getName(), "ne tak potok");

        }
        catch (Throwable e){
            Log.d("ne tak", String.valueOf(e));
        }
      //        SystemClock.sleep(get_random());

        return arrayList;
    }

}


