package com.example.fakenews.ui.notifications.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakenews.DB.models.Country;
import com.example.fakenews.R;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.mVH> {
    private ArrayList<Country> countries;
    private Context context;
    private onItemClick onItemClick;

    public CountryAdapter(ArrayList<Country> countries, onItemClick onItemClick) {
        this.countries = countries;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public mVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new mVH(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull mVH holder, int position) {
        holder.textView1.setText(countries.get(position).getShort_name());
        holder.textView2.setText(countries.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


    public class mVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView1, textView2;
        onItemClick onItemClick;

        public mVH(@NonNull View itemView, onItemClick onItemClick) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tv_country1);
            textView2 = itemView.findViewById(R.id.tv_country2);
            this.onItemClick = onItemClick;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            onItemClick.click(getAdapterPosition());
        }
    }

    public Country getCountryAt(int position) {
        return countries.get(position);
    }

    public interface onItemClick {
        void click(int position);
    }
}
