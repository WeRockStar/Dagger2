package com.werocksta.dagger2demo.di.component;

import com.werocksta.dagger2demo.di.ActivityScope;
import com.werocksta.dagger2demo.di.module.ActivityModule;
import com.werocksta.dagger2demo.view.fragment.MainFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

}
