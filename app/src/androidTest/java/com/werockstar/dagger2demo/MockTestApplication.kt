package com.werockstar.dagger2demo

import com.werockstar.dagger2demo.di.MockHttpModule
import com.werockstar.dagger2demo.di.component.DaggerTestComponent
import com.werockstar.dagger2demo.di.module.ApplicationModule

class MockTestApplication : MainApplication() {

    override fun createComponent() {
        component=  DaggerTestComponent.builder()
                .applicationModule(ApplicationModule(this))
                .mockHttpModule(MockHttpModule())
                .build()
    }
}