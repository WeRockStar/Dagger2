package com.werocksta.dagger2demo.di.component;

import com.werocksta.dagger2demo.di.module.AndroidModule;
import com.werocksta.dagger2demo.di.module.ApplicationModule;
import com.werocksta.dagger2demo.di.module.HttpModule;
import com.werocksta.dagger2demo.manager.ApiService;
import com.werocksta.dagger2demo.view.activity.MainActivity;
import com.werocksta.dagger2demo.view.fragment.MainFragment;
import com.werocksta.dagger2demo.view.fragment.RepoFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {HttpModule.class, ApplicationModule.class, AndroidModule.class})
public interface AppComponent {
    void inject(MainFragment fragment);

    void inject(MainActivity fragment);

    void inject(RepoFragment fragment);

    ApiService api();
}
