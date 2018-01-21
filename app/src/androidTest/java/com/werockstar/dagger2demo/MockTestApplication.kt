package com.werockstar.dagger2demo

import com.werockstar.dagger2demo.di.component.DiComponent
import com.werockstar.dagger2demo.di.module.ApplicationModule
import com.werockstar.dagger2demo.view.activity.DaggerMainActivityTest_TestComponent

class MockTestApplication : MainApplication() {

    override fun createComponent(): DiComponent {
        return DaggerMainActivityTest_TestComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}