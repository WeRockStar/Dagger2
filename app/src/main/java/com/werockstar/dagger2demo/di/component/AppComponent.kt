package com.werockstar.dagger2demo.di.component

import com.werockstar.dagger2demo.di.module.AndroidModule
import com.werockstar.dagger2demo.di.module.ApplicationModule
import com.werockstar.dagger2demo.di.module.HttpModule
import com.werockstar.dagger2demo.di.module.RxThreadModule
import com.werockstar.dagger2demo.view.activity.MainActivity
import com.werockstar.dagger2demo.view.fragment.MainFragment
import com.werockstar.dagger2demo.view.fragment.RepoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(HttpModule::class,
        ApplicationModule::class, AndroidModule::class,
        RxThreadModule::class))
interface AppComponent {
    fun inject(fragment: MainFragment)

    fun inject(fragment: MainActivity)

    fun inject(fragment: RepoFragment)
}
