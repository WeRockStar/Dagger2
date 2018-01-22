package com.werockstar.dagger2demo.di

import com.werockstar.dagger2demo.di.module.RxThreadModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

class MockRxThreadModule : RxThreadModule() {

    companion object {
        const val mainThread = "mainThread"
        const val ioThread = "ioThread"
    }


    @Named(mainThread)
    override fun provideAndroidSchedulers() = AndroidSchedulers.mainThread()

    @Named(ioThread)
    override fun provideSchedulersIO() = Schedulers.trampoline()
}