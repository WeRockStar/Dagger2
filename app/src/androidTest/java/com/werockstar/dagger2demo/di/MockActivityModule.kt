package com.werockstar.dagger2demo.di

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock

@Module
class MockActivityModule() {

    @PerActivity
    @Provides
    @ActivityContext
    fun provideContext(): Context = mock(Activity::class.java)
}
