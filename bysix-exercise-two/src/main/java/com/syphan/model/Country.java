package com.syphan.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("country")
    private String name;
    private String[] languages;

    public Country(String name, String[] languages) {
        this.name = name;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public String[] getLanguages() {
        return languages;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }
}
