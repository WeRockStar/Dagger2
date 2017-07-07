package com.werocksta.dagger2demo.di.module


import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager

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

    @Provides
    @Singleton
    fun provideInputMethod() = application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


}
