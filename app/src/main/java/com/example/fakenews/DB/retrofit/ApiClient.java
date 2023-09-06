package com.example.fakenews.DB.retrofit;

import androidx.lifecycle.MutableLiveData;

import com.example.fakenews.DB.models.Headline;
import com.example.fakenews.DB.models.Sources;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // https://newsapi.org/v2/top-headlines?country=us&apiKey=331303f4d4a7435fa9ee432a8ab905ed
    // OLD // public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //?country=eg&apiKey=331303f4d4a7435fa9ee432a8ab905ed

    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String apiKey = "331303f4d4a7435fa9ee432a8ab905ed";
    private ApiServices apiServices;
    private static ApiClient INSTANCE;

    public ApiClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(chain -> {
            Request request = chain
                    .request()
                    .newBuilder()
                    .addHeader("Accept", "application/json").build();
            return chain.proceed(request);
        });
        client.addInterceptor(httpLoggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);
    }

    public static ApiClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public Call<Headline> getHeadlines(String country) {
        return apiServices.getHeadlines(country, apiKey);
    }

    public Call<String> getState() {
        return apiServices.getStatus();
    }

    public Call<Sources> getSources() {
        return apiServices.getSources();
    }

    public Call<Headline> getHeadlineWithSource(String source, String apiKey) {
        return apiServices.getHeadLineWithSource(source, apiKey);
    }

}
