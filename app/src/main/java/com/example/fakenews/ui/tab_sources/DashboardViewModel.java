package com.example.fakenews.ui.tab_sources;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fakenews.DB.Repo;
import com.example.fakenews.DB.models.Source2;
import com.example.fakenews.DB.models.Sources;
import com.example.fakenews.DB.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends AndroidViewModel {

    private Repo repo;
    private List<Source2> sourcesList = new ArrayList<>();
    private LiveData<List<Source2>> allSourcesRoom;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        allSourcesRoom = repo.getAllSources();
    }

    public void insert(Source2 source2) {
        repo.insert(source2);
    }

    public void update(Source2 source2) {
        repo.update(source2);
    }

    public void delete(Source2 source2) {
        repo.delete(source2);
    }

    public void deleteAllNotes() {
        repo.deleteAllSources();
    }

    public LiveData<List<Source2>> getAllSources() {
        return allSourcesRoom;
    }


    public void getSources() {
        ApiClient.getINSTANCE().getSources().enqueue(new Callback<Sources>() {
            @Override
            public void onResponse(Call<Sources> call, Response<Sources> response) {
                sourcesList.addAll(response.body().getSource2List());
                for (int i = 0; i < sourcesList.size(); i++) {
                    String id = sourcesList.get(i).getId();
                    String name = sourcesList.get(i).getName();
                    String description = sourcesList.get(i).getDescription();
                    String url = sourcesList.get(i).getUrl();
                    String category = sourcesList.get(i).getCategory();
                    String language = sourcesList.get(i).getLanguage();
                    String country = sourcesList.get(i).getCountry();
                    Source2 source2 = new Source2(id, name, description, url, category, language, country);
                    repo.insert(source2);
                }
            }

            @Override
            public void onFailure(Call<Sources> call, Throwable t) {


            }
        });
    }

}