package com.codepath.flixster.Models;



import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    int rating;
   // Movie posterSize[];


    public static final String TAG = "Movie";

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getInt("vote_average");

//        JSONObject jsonObject2 = jsonObject;          Request images location like in main activity
//        JSONObject images = jsonObject2.getJSONObject("images");
//              Log.i(TAG, "Images size:" + images.length());

        //        for (int i = 0; i < images.length(); i++) {
//            posterSize[i] = images.;
//            Log.i(TAG, i + "size: " + posterSize[i]);
//        }
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }


    public String getPosterPath() {
        if(rating >= 5){
            return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
        }
        else {
            return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
        }
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
