package com.example.fakenews.ui.sourceDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fakenews.DB.models.Article;
import com.example.fakenews.DB.models.Headline;
import com.example.fakenews.DB.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceDetailsViewModel extends ViewModel {
    public MutableLiveData<List<Article>> articlesWithSource = new MutableLiveData<>();

}