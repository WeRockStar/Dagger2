package com.werockstar.dagger2demo

import android.app.Application
import com.werockstar.dagger2demo.di.component.AppComponent
import com.werockstar.dagger2demo.di.component.DaggerAppComponent
import com.werockstar.dagger2demo.di.module.AndroidModule
import com.werockstar.dagger2demo.di.module.ApplicationModule
import com.werockstar.dagger2demo.di.module.HttpModule

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

