package com.example.fakenews.ui.HeadlineDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fakenews.DB.models.Headline;
import com.example.fakenews.DB.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HeadlineDetailsViewModel extends ViewModel {
    public MutableLiveData<String> author = new MutableLiveData<>();

//    public void getArticleDetails() {
//        ApiClient.getINSTANCE().getHeadlines("us").enqueue(new Callback<Headline>() {
//            @Override
//            public void onResponse(Call<Headline> call, Response<Headline> response) {
//                author.setValue(response.body().getArticles().get(1).getAuthor());
//            }
//
//            @Override
//            public void onFailure(Call<Headline> call, Throwable t) {
//
//            }
//        });
//    }

    public MutableLiveData<String> getAuthor() {
        return author;
    }
}