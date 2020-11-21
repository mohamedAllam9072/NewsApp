package com.example.fakenews.ui.headlineDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


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