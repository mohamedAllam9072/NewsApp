package com.example.fakenews.ui.tab_home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakenews.DB.models.Article;
import com.example.fakenews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.mVH> {
    private List<Article> articleList;
    private Context context;

    public HeadlineAdapter(Context context, List<Article> articleList) {
        this.articleList = articleList;
        this.context = context;
    }

    @NonNull
    @Override
    public mVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_headline, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mVH holder, int position) {
        holder.textView1.setText(articleList.get(position).getTitle());
        holder.textView2.setText(articleList.get(position).getSourceHeadline().getName());
        holder.textView3.setText(articleList.get(position).getPublishedAt());
        try {
            Picasso.with(context)
                    .load(articleList.get(position).getUrlToImage())
                    .centerCrop()
                    .fit()
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_error)
                    .into(holder.imageView);
        } catch (Exception e) {
        }

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    public class mVH extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1, textView2, textView3;

        public mVH(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tv1_headline);
            textView2 = itemView.findViewById(R.id.tv2_headline);
            textView3 = itemView.findViewById(R.id.tv3_headline);
            imageView = itemView.findViewById(R.id.iv_headline);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putString("author", articleList.get(getAdapterPosition()).getAuthor());
                    args.putString("title", articleList.get(getAdapterPosition()).getTitle());
                    args.putString("description", articleList.get(getAdapterPosition()).getDescription());
                    args.putString("url", articleList.get(getAdapterPosition()).getUrl());
                    args.putString("urlToImage", articleList.get(getAdapterPosition()).getUrlToImage());
                    args.putString("publishedAt", articleList.get(getAdapterPosition()).getPublishedAt());
                    args.putString("content", articleList.get(getAdapterPosition()).getContent());
                    args.putString("source_id", articleList.get(getAdapterPosition()).getSourceHeadline().getSource1_id());
                    args.putString("source_name", articleList.get(getAdapterPosition()).getSourceHeadline().getName());

                    Navigation.findNavController(itemView).navigate(R.id.action_home_to_headlineDetails, args);

                }
            });

        }
    }
}
