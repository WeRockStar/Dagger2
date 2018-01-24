package com.werockstar.dagger2demo

import com.werockstar.dagger2demo.di.MockHttpModule
import com.werockstar.dagger2demo.di.MockRxThreadModule
import com.werockstar.dagger2demo.di.component.AppComponent
import com.werockstar.dagger2demo.di.module.ApplicationModule
import com.werockstar.dagger2demo.view.activity.DaggerMainActivityTest_TestComponent

class MockTestApplication : MainApplication() {

    override fun createComponent(): AppComponent {
        return DaggerMainActivityTest_TestComponent.builder()
                .applicationModule(ApplicationModule(this))
                .httpModule(MockHttpModule())
                .rxThreadModule(MockRxThreadModule())
                .build()
    }
}