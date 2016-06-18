package com.werocksta.dagger2demo.di.component;

import com.werocksta.dagger2demo.di.module.AppModule;
import com.werocksta.dagger2demo.view.fragment.MainFragment;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainFragment fragment);
}
