package com.werockstar.dagger2demo.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class RxThreadModule {

    companion object {
        const val mainThread = "mainThread"
        const val ioThread = "ioThread"
    }

    @Singleton
    @Provides
    @Named(mainThread)
    fun provideAndroidSchedulers() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named(ioThread)
    fun provideSchedulersIO() = Schedulers.io()
}