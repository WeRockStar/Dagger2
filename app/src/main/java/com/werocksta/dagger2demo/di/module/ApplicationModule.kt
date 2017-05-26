package com.werocksta.dagger2demo.di.module


import android.app.Application
import android.content.Context

import com.werocksta.dagger2demo.di.ApplicationContext

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = this.application


}
