package com.werockstar.dagger2demo.di.module

import android.app.Activity
import android.content.Context

import com.werockstar.dagger2demo.di.ActivityContext
import com.werockstar.dagger2demo.di.PerActivity

import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @PerActivity
    @Provides
    @ActivityContext
    fun provideContext(): Context = activity
}
