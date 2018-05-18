package com.example.a37_1.e_advertisement.Api;

/**
 * Created by Vova0199 on 07.05.2018.
 */

import com.example.a37_1.e_advertisement.model.LevelModel;
import com.example.a37_1.e_advertisement.model.LevelsList;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */

    @GET("/api/mcu/check_level/1")
    Call<LevelsList> getMyLevel();

}