package com.example.fakenews.DB.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sources {
    @SerializedName("status")
    private String status;
    @SerializedName("sources")
    private List<Source2> source2List;

    public Sources(String status, List<Source2> source2List) {
        this.status = status;
        this.source2List = source2List;
    }

    public String getStatus() {
        return status;
    }

    public List<Source2> getSource2List() {
        return source2List;
    }
}
