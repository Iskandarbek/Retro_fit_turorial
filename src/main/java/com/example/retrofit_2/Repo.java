package com.example.retrofit_2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repo {
    private String name;

    public Repo(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    @SerializedName("html_url")
    private String link;
}
