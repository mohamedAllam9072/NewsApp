package com.example.fakenews.DB.models;

public class Country {

    private String name;
    private String short_name;

    public Country(String name, String short_name) {
        this.name = name;
        this.short_name = short_name;
    }

    public String getName() {
        return name;
    }

    public String getShort_name() {
        return short_name;
    }
}
