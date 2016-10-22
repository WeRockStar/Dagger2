package com.werocksta.dagger2demo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoCollection {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String nameRepo;

    @SerializedName("full_name")
    private String fullNameRepo;

    @SerializedName("language")
    private String language;

    @SerializedName("html_url")
    private String htmlUrl;

    public int getId() {
        return id;
    }

    public String getNameRepo() {
        return nameRepo;
    }

    public String getFullNameRepo() {
        return fullNameRepo;
    }

    public String getLanguage() {
        return language;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}
