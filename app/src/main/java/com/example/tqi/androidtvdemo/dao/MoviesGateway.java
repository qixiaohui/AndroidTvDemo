package com.example.tqi.androidtvdemo.dao;

import android.util.Log;

import com.example.tqi.pojo.Pojo;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by TQi on 2/18/16.
 */
public class MoviesGateway {
    public String getMovies(){
        try {
            URL url = new URL("http://10.0.0.8:3000");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String stream = readStream(con.getInputStream());
            return stream;
        } catch(IOException e){
            Log.e("Http exception", e.toString());
        }
        return null;
    }

    public String getRelated(String genre){
        try {
            URL url = new URL("http://10.0.0.8:3000/related/"+genre);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String stream = readStream(con.getInputStream());
            return stream;
        } catch(IOException e){
            Log.e("Http exception", e.toString());
        }
        return null;
    }

    private String readStream(InputStream in){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
