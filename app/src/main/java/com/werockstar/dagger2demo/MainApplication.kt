package com.werockstar.dagger2demo

import android.app.Application
import com.werockstar.dagger2demo.di.component.AppComponent
import com.werockstar.dagger2demo.di.component.DaggerAppComponent
import com.werockstar.dagger2demo.di.module.ApplicationModule
import com.werockstar.dagger2demo.di.module.HttpModule

open class MainApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        createComponent()
    }

    protected open fun createComponent() {
        component = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this))
                .httpModule(HttpModule())
                .build()
    }
}

