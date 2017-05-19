package com.werocksta.dagger2demo.api

import com.werocksta.dagger2demo.model.GithubUserCollection
import com.werocksta.dagger2demo.model.RepoCollection

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface GithubAPI {

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Observable<GithubUserCollection>

    @GET("users/{user}/repos")
    fun getRepo(@Path("user") user: String): Observable<List<RepoCollection>>
}
