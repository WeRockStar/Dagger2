package com.werocksta.dagger2demo.model

import com.google.gson.annotations.SerializedName

class RepoCollection constructor(@SerializedName("id") val id: Int, @SerializedName("name") val nameRepo: String,
                                 @SerializedName("full_name") val fullNameRepo: String, @SerializedName("language") val language: String,
                                 @SerializedName("html_url") val htmlUrl: String)
