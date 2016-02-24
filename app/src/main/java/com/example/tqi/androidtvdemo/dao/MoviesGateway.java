package com.example.tqi.androidtvdemo.dao;

import android.util.Log;

import com.example.tqi.pojo.Movie;
import com.example.tqi.pojo.Pojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by TQi on 2/18/16.
 */
public interface MoviesGateway {

    @GET("movies")
    Call<Pojo> getMovies();

    @GET("related/{genre}")
    Call<List<Movie>> getRelated(@Path("genre") String genre);

}
