package com.werocksta.dagger2demo.di.component

import com.werocksta.dagger2demo.di.PerActivity
import com.werocksta.dagger2demo.di.module.ActivityModule

import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent
