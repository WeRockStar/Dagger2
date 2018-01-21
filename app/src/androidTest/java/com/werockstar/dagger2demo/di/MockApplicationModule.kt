package com.werockstar.dagger2demo.di


import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockApplicationModule(val application: Application) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Application = application

    @Provides
    @Singleton
    fun provideInputMethod() = application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


}
