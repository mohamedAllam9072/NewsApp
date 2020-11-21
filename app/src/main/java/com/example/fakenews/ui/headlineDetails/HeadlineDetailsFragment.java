package com.example.fakenews.ui.headlineDetails;

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

import com.example.fakenews.R;
import com.squareup.picasso.Picasso;

public class HeadlineDetailsFragment extends Fragment {

    private HeadlineDetailsViewModel viewModel;
    private ImageView imageView, iv_url;
    private TextView tv_author, tv_title, tv_description, tv_publishedAt, tv_content, tv_source;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_headline_details, container, false);
        init(root);
        setImageView();
        setTexts();

        return root;
    }

    private void init(View root) {

        imageView = root.findViewById(R.id.iv_headline_d);
        tv_author = root.findViewById(R.id.tv_author_headline_d);
        tv_title = root.findViewById(R.id.tv_title_headline_d);
        tv_description = root.findViewById(R.id.tv_description_headline_d);
        iv_url = root.findViewById(R.id.iv_link_headline_d);
        tv_publishedAt = root.findViewById(R.id.tv_publishedAt_headline_d);
        tv_content = root.findViewById(R.id.tv_content_headline_d);
        tv_source = root.findViewById(R.id.tv_source_headline_d);


    }

    private void setImageView() {
        try {
            Picasso.with(getActivity())
                    .load(getArguments().getString("urlToImage"))
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_error)
                    .into(imageView);
        } catch (Exception e) {
            throw e;
        }
    }

    private void setTexts() {
        tv_author.setText(getArguments().getString("author"));
        tv_title.setText(getArguments().getString("title"));
        tv_description.setText(getArguments().getString("description"));
        tv_publishedAt.setText(getArguments().getString("publishedAt"));
        tv_content.setText(getArguments().getString("content"));
        tv_source.setText(getArguments().getString("source_name"));
        String source_id = getArguments().getString("source_id");
        iv_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getArguments().getString("url")));
                    startActivity(intent);
                    Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }

            }
        });


    }
}