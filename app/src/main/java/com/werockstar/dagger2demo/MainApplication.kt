package com.werockstar.dagger2demo

import android.app.Application
import com.werockstar.dagger2demo.di.component.DiComponent
import com.werockstar.dagger2demo.di.module.AndroidModule
import com.werockstar.dagger2demo.di.module.ApplicationModule
import com.werockstar.dagger2demo.di.module.HttpModule
import com.werockstar.dagger2demo.di.module.RxThreadModule
import dagger.Component
import javax.inject.Singleton

open class MainApplication : Application() {

    @Singleton
    @Component(modules = arrayOf(HttpModule::class,
            ApplicationModule::class,
            AndroidModule::class,
            RxThreadModule::class))
    interface ApplicationComponent : DiComponent

    lateinit var component: DiComponent

    override fun onCreate() {
        super.onCreate()

        component = createComponent()
    }

    protected open fun createComponent(): DiComponent {
        return DaggerMainApplication_ApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}

