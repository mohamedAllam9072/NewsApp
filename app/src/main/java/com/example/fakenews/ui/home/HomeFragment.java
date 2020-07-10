package com.example.fakenews.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakenews.DB.models.Article;
import com.example.fakenews.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private RecyclerView recyclerView;
    private HeadlineAdapter adapter;
    private List<Article> articles = new ArrayList<>();
    private View root;
    private String country = "eg";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        viewModel();
        actionbar();
        rv();

        return root;
    }

    private void rv() {
        recyclerView = root.findViewById(R.id.rv_posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HeadlineAdapter(getContext(), articles);
        recyclerView.setAdapter(adapter);

    }

    private void actionbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout = root.findViewById(R.id.collapsingToolbar);
        AppBarLayout appBarLayout = root.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("BREAKING NEWS");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        appBarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_countryFragment);
            }
        });
    }

    private void viewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        viewModel.getDefaultCountryShort().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                country = s;
                Toast.makeText(getContext(), "home " + country, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getArticles(country);
        viewModel.getAllArticlesRoom().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                adapter.setList(articles);
            }
        });
    }


}