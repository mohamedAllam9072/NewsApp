package com.example.fakenews.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakenews.DB.models.Source2;
import com.example.fakenews.R;

import java.util.ArrayList;
import java.util.List;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.mVH> {
    private List<Source2> source2List = new ArrayList<>();

    @NonNull
    @Override
    public mVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_source, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mVH holder, int position) {
        holder.textView1.setText(source2List.get(position).getName());
        holder.textView2.setText(source2List.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return source2List.size();
    }

    public void setList(List<Source2> source2List) {
        this.source2List = source2List;
        notifyDataSetChanged();
    }

    public class mVH extends RecyclerView.ViewHolder {
        TextView textView1, textView2;

        public mVH(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tv1_source);
            textView2 = itemView.findViewById(R.id.tv2_source);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args2 = new Bundle();
                    args2.putString("src_id", source2List.get(getAdapterPosition()).getId());
                    args2.putString("src_name", source2List.get(getAdapterPosition()).getName());
                    args2.putString("src_description", source2List.get(getAdapterPosition()).getDescription());
                    args2.putString("src_url", source2List.get(getAdapterPosition()).getUrl());
                    args2.putString("src_category", source2List.get(getAdapterPosition()).getCategory());
                    args2.putString("src_country", source2List.get(getAdapterPosition()).getCountry());
                    args2.putString("src_language", source2List.get(getAdapterPosition()).getLanguage());

                    Navigation.findNavController(itemView).navigate(R.id.action_navigation_dashboard_to_sourceDetailsFragment, args2);

                }
            });

        }
    }
}
