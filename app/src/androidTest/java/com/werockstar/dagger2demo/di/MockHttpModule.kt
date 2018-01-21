package com.werockstar.dagger2demo.di

import com.werockstar.dagger2demo.api.GithubAPI
import com.werockstar.dagger2demo.util.URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class MockHttpModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return mock(OkHttpClient::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)
}