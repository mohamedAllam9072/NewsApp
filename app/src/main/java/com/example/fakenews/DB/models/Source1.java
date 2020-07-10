package com.example.fakenews.DB.models;

import com.google.gson.annotations.SerializedName;

public class Source1 {
    @SerializedName("id")
    private String source1_id;
    private String name;

    public Source1() {
    }

    public Source1(String id, String name) {
        this.source1_id = id;
        this.name = name;
    }

    public String getSource1_id() {
        return source1_id;
    }

    public String getName() {
        return name;
    }

    public void setSource1_id(String source1_id) {
        this.source1_id = source1_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}