package com.werocksta.dagger2demo.model;


import com.google.gson.annotations.SerializedName;

public class GithubUserInfoCollection {

    @SerializedName("login")
    private String username;

    @SerializedName("id")
    private int id;

    @SerializedName("avatar_url")
    private String imageUrl;

    @SerializedName("repos_url")
    private String repoUrl;
}
