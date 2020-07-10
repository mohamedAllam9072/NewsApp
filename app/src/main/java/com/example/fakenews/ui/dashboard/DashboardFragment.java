package com.example.fakenews.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakenews.DB.models.Source2;
import com.example.fakenews.R;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel viewModel;
    private RecyclerView recyclerView;
    private SourceAdapter adapter = new SourceAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = root.findViewById(R.id.rv_source);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        viewModel.getSources();
        viewModel.getAllSources().observe(getViewLifecycleOwner(), new Observer<List<Source2>>() {
            @Override
            public void onChanged(List<Source2> source2s) {
                adapter.setList(source2s);
            }
        });
        recyclerView.setAdapter(adapter);

        return root;
    }

}