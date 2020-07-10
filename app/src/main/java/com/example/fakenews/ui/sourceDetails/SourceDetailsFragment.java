package com.example.fakenews.ui.sourceDetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakenews.DB.models.Article;
import com.example.fakenews.DB.models.Headline;
import com.example.fakenews.DB.retrofit.ApiClient;
import com.example.fakenews.R;
import com.example.fakenews.ui.home.HeadlineAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceDetailsFragment extends Fragment {

    private List<Article> articlesWithSource = new ArrayList<>();
    private TextView src_name, src_category, src_description;
    private ImageView src_link;
    private RecyclerView recyclerView;
    private HeadlineAdapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adapter = new HeadlineAdapter(container.getContext(), articlesWithSource);
        View root = inflater.inflate(R.layout.source_details_fragment, container, false);
        init(root);
        getArticlesWithSource(getArguments().getString("src_id"));
        recyclerView.setAdapter(adapter);
        setTexts();


        return root;
    }

    private void init(View root) {
        src_name = root.findViewById(R.id.tv_name_source_d);
        src_category = root.findViewById(R.id.tv_category_source_d);
        src_description = root.findViewById(R.id.tv_description_source_d);
        src_link = root.findViewById(R.id.iv_link_source_d);
        recyclerView = root.findViewById(R.id.rv_source_d);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setTexts() {
        src_name.setText(getArguments().getString("src_name"));
        src_description.setText(getArguments().getString("src_description"));
        src_category.setText(getArguments().getString("src_category"));

        String src_id, src_language, src_country;
        src_id = getArguments().getString("src_id");
        src_language = getArguments().getString("src_language");
        src_country = getArguments().getString("src_country");


        src_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getArguments().getString("src_url")));
                    startActivity(intent);
                    Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }

            }
        });

    }

    public void getArticlesWithSource(String source_name) {
        ApiClient.getINSTANCE().getHeadlineWithSource(source_name, ApiClient.apiKey).enqueue(new Callback<Headline>() {
            @Override
            public void onResponse(Call<Headline> call, Response<Headline> response) {
                articlesWithSource.addAll(response.body().getArticles());
                adapter.setList(articlesWithSource);
            }

            @Override
            public void onFailure(Call<Headline> call, Throwable t) {

            }
        });
    }
}