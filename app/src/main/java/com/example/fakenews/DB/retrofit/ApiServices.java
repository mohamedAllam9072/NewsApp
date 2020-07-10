package com.example.fakenews.DB.retrofit;

import com.example.fakenews.DB.models.Headline;
import com.example.fakenews.DB.models.Sources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("top-headlines")
    Call<Headline> getHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);

    //https://newsapi.org/v2/
    @GET("top-headlines?country=us/status&apiKey=331303f4d4a7435fa9ee432a8ab905ed")
    Call<String> getStatus();

    @GET("sources?apiKey=331303f4d4a7435fa9ee432a8ab905ed")
    Call<Sources> getSources();

    @GET("top-headlines")
    Call<Headline> getHeadLineWithSource(@Query("sources") String source, @Query("apiKey") String apiKey);
    //bbc-news
}
