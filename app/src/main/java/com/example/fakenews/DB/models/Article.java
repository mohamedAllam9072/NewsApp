package com.example.fakenews.DB.models;


import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "articlesTable")
public class Article {
    @SerializedName("author")
    private String author;
    @SerializedName("title")
    @NonNull
    @PrimaryKey
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("content")
    private String content;
    @SerializedName("source")
    @Embedded
    private Source1 sourceHeadline;

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt, String content, Source1 sourceHeadline) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.sourceHeadline = sourceHeadline;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public Source1 getSourceHeadline() {
        return sourceHeadline;
    }

}

