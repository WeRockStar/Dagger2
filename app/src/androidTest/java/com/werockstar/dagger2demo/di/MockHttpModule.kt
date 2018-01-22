package com.werockstar.dagger2demo.di

import com.werockstar.dagger2demo.api.GithubAPI
import com.werockstar.dagger2demo.di.module.HttpModule
import okhttp3.OkHttpClient
import org.mockito.Mockito
import retrofit2.Retrofit

class MockHttpModule : HttpModule() {
    override fun provideHttpLogging(): OkHttpClient {
        return Mockito.mock(OkHttpClient::class.java)
    }

    override fun provideApiService(retrofit: Retrofit): GithubAPI {
        return Mockito.mock(GithubAPI::class.java)
    }
}

