package com.werockstar.dagger2demo.di.module

import android.support.customtabs.CustomTabsIntent

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AndroidModule {

    @Provides
    @Singleton
    fun provideCustomTabsBuilder(): CustomTabsIntent.Builder = CustomTabsIntent.Builder()

    @Provides
    @Singleton
    fun provideCustomTabsIntent(builder: CustomTabsIntent.Builder): CustomTabsIntent = builder.build()
    
}
