package com.werockstar.dagger2demo.model


import com.google.gson.annotations.SerializedName

data class GithubUserCollection constructor(@SerializedName("login") val username: String = "", @SerializedName("id") val id: Int = 0,
                                            @SerializedName("avatar_url") val imageUrl: String = "",
                                            @SerializedName("repos_url") val repoUrl: String = "")
