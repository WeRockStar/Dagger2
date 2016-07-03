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

    public int getId() {
        return id;
    }

    public String getNameRepo() {
        return nameRepo;
    }

    public String getFullNameRepo() {
        return fullNameRepo;
    }

}
