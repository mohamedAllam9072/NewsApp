package com.example.fakenews.ui.tab_settings.country;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakenews.DB.models.Country;
import com.example.fakenews.R;
import com.example.fakenews.ui.tab_home.HomeViewModel;

import java.util.ArrayList;

public class CountryFragment extends Fragment implements CountryAdapter.onItemClick {
    private HomeViewModel viewModel;
    private ArrayList<Country> countryArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView tv_default_country;
    private CountryAdapter adapter;
    private View root;

    public SharedPreferences sharedPref;
    private String country_s = "eg";
    private String country = "Egypt";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        root = inflater.inflate(R.layout.fragment_country, container, false);
        tv_default_country = root.findViewById(R.id.tv_default_country);
        getList();
        recyclerView = root.findViewById(R.id.rv_country);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CountryAdapter(countryArrayList, this);
        recyclerView.setAdapter(adapter);
        //    tv_default_country.setText("Default Country : " + viewModel.getDefaultCountry().getValue());
        return root;
    }

    public void getList() {
        countryArrayList.add(0, new Country("Egypt", "eg"));
        countryArrayList.add(1, new Country("United Arab Emirates", "ae"));
        countryArrayList.add(2, new Country("United Kingdom", "gb"));
        countryArrayList.add(3, new Country("United States of America", "us"));
        countryArrayList.add(4, new Country("Australia", "au"));
        countryArrayList.add(5, new Country("Belgium", "be"));
        countryArrayList.add(6, new Country("Bulgaria", "bg"));
        countryArrayList.add(7, new Country("Brazil", "br"));
        countryArrayList.add(8, new Country("Canada", "ca"));
        countryArrayList.add(9, new Country("Switzerland", "ch"));
        countryArrayList.add(10, new Country("China", "cn"));
        countryArrayList.add(11, new Country("Colombia", "co"));
        countryArrayList.add(12, new Country("Cuba", "cu"));
        countryArrayList.add(13, new Country("Czechia", "cz"));
        countryArrayList.add(14, new Country("Germany", "de"));
        countryArrayList.add(15, new Country("Zambia", "za"));
        countryArrayList.add(16, new Country("France", "fr"));
        countryArrayList.add(17, new Country("Argentina", "ar"));
        countryArrayList.add(18, new Country("Greece", "gr"));
        countryArrayList.add(19, new Country("Hong Kong", "hk"));
        countryArrayList.add(20, new Country("Hungary", "hu"));
        countryArrayList.add(21, new Country("Indonesia", "id"));
        countryArrayList.add(22, new Country("Ireland", "ie"));
        countryArrayList.add(23, new Country("Israel", "il"));
        countryArrayList.add(24, new Country("India", "in"));
        countryArrayList.add(25, new Country("Italy", "it"));
        countryArrayList.add(26, new Country("Japan", "jp"));
        countryArrayList.add(27, new Country("South Korea", "kr"));
        countryArrayList.add(28, new Country("Lithuania", "lt"));
        countryArrayList.add(29, new Country("Latvia", "lv"));
        countryArrayList.add(30, new Country("Morocco", "ma"));
        countryArrayList.add(31, new Country("Mexico", "mx"));
        countryArrayList.add(32, new Country("Malaysia", "my"));
        countryArrayList.add(33, new Country("Nigeria", "ng"));
        countryArrayList.add(34, new Country("Netherlands", "nl"));
        countryArrayList.add(35, new Country("Norway", "no"));
        countryArrayList.add(36, new Country("New Zealand", "nz"));
        countryArrayList.add(37, new Country("Philippines", "ph"));
        countryArrayList.add(38, new Country("Poland", "pl"));
        countryArrayList.add(39, new Country("Portugal", "pt"));
        countryArrayList.add(40, new Country("Romania", "ro"));
        countryArrayList.add(41, new Country("Serbia", "rs"));
        countryArrayList.add(42, new Country("Russia", "ru"));
        countryArrayList.add(43, new Country("Saudi Arabia", "sa"));
        countryArrayList.add(44, new Country("Sweden", "se"));
        countryArrayList.add(45, new Country("Singapore", "sg"));
        countryArrayList.add(46, new Country("Slovenia", "si"));
        countryArrayList.add(47, new Country("Slovakia", "sk"));
        countryArrayList.add(48, new Country("Thailand", "th"));
        countryArrayList.add(49, new Country("Turkey", "tr"));
        countryArrayList.add(50, new Country("Taiwan", "tw"));
        countryArrayList.add(51, new Country("Ukraine", "ua"));
        countryArrayList.add(52, new Country("Austria", "at"));
        countryArrayList.add(53, new Country("Venezuela", "ve"));


    }

    @Override
    public void click(int position) {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.default_Country_s), adapter.getCountryAt(position).getShort_name());
        editor.putString(getString(R.string.default_Country), adapter.getCountryAt(position).getName());
        editor.commit();
        country_s = getResources().getString(R.string.default_Country_s);
        country = getResources().getString(R.string.default_Country);

        tv_default_country.setText("Default Country : " + country);

//        String country=getResources().getString(R.string.default_Country);

        Dialog();

    }

    private void Dialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog, null);
        TextView textView = view.findViewById(R.id.tv_dialog);
        textView.setText(country);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //    Navigation.findNavController(root).navigate(R.id.action_countryFragment_to_navigation_home);

                    }
                })
                .create()
                .show();

    }

}