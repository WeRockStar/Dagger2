package com.werocksta.dagger2demo

import android.app.Application

import com.werocksta.dagger2demo.di.component.AppComponent
import com.werocksta.dagger2demo.di.component.DaggerAppComponent
import com.werocksta.dagger2demo.di.module.AndroidModule
import com.werocksta.dagger2demo.di.module.ApplicationModule
import com.werocksta.dagger2demo.di.module.HttpModule

class MainApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this))
                .androidModule(AndroidModule())
                .httpModule(HttpModule())
                .build()
    }
}

