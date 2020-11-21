package com.example.fakenews.ui.tab_settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.fakenews.R;

public class SettingsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        Btn_country(root);
        Btn_history(root);

        return root;
    }

    private void Btn_country(View root) {
        Button button = root.findViewById(R.id.btn_country);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_settings_to_country);

            }
        });
    }

    private void Btn_history(View root) {
        Button button = root.findViewById(R.id.btn_history);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_settings_to_history);

            }
        });
    }
}