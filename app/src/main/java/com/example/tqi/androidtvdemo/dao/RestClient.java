package com.example.tqi.androidtvdemo.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TQi on 2/23/16.
 */
public class RestClient {
    static final String baseUrl = "http://192.168.1.18:3000/";

    public static MoviesGateway getGateway(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MoviesGateway.class);
    }
}
