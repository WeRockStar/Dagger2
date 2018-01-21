package com.werockstar.dagger2demo.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockRxThreadModule {

    companion object {
        const val mainThread = "mainThread"
        const val ioThread = "ioThread"
    }

    @Singleton
    @Provides
    @Named(mainThread)
    fun provideAndroidSchedulers() = Schedulers.trampoline()

    @Provides
    @Singleton
    @Named(ioThread)
    fun provideSchedulersIO() = Schedulers.trampoline()
}