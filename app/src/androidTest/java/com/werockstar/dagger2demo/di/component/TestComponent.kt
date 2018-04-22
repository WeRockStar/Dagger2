package com.werockstar.dagger2demo.di.component

import com.werockstar.dagger2demo.di.MockHttpModule
import com.werockstar.dagger2demo.di.module.AndroidModule
import com.werockstar.dagger2demo.di.module.ApplicationModule
import com.werockstar.dagger2demo.di.module.RxThreadModule
import com.werockstar.dagger2demo.view.activity.MainActivityTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(MockHttpModule::class), (ApplicationModule::class),
    (AndroidModule::class), (RxThreadModule::class)]
)
interface TestComponent : AppComponent {
    fun inject(activity: MainActivityTest)
}