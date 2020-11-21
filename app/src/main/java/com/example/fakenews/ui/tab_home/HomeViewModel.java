package com.example.fakenews.ui.tab_home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fakenews.DB.Repo;
import com.example.fakenews.DB.models.Article;
import com.example.fakenews.DB.models.Headline;
import com.example.fakenews.DB.models.Source1;
import com.example.fakenews.DB.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {

    private Repo repo;
    private List<Article> articleList22 = new ArrayList<>();
    private LiveData<List<Article>> allArticlesRoom;

    private MutableLiveData<String> defaultCountry = new MutableLiveData<>();
    private MutableLiveData<String> defaultCountryShort = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        allArticlesRoom = repo.getAllArticles();
    }


    public void getArticles(String country) {
        ApiClient.getINSTANCE().getHeadlines(country).enqueue(new Callback<Headline>() {
            @Override
            public void onResponse(Call<Headline> call, Response<Headline> response) {
                articleList22.addAll(response.body().getArticles());
                for (int i = 0; i < articleList22.size(); i++) {
                    String author = articleList22.get(i).getAuthor();
                    String title = articleList22.get(i).getTitle();
                    String description = articleList22.get(i).getDescription();
                    String url = articleList22.get(i).getUrl();
                    String urlToImage = articleList22.get(i).getUrlToImage();
                    String publishedAt = articleList22.get(i).getPublishedAt();
                    String content = articleList22.get(i).getContent();
                    String source1_id = articleList22.get(i).getSourceHeadline().getSource1_id();
                    String source1_name = articleList22.get(i).getSourceHeadline().getName();
                    Source1 source1 = new Source1(source1_id, source1_name);
                    Article article = new Article(author, title, description, url, urlToImage, publishedAt, content, source1);
                    repo.insertArticle(article);
                }
            }

            @Override
            public void onFailure(Call<Headline> call, Throwable t) {
            }
        });
    }

    public LiveData<List<Article>> getAllArticlesRoom() {
        return allArticlesRoom;
    }

}