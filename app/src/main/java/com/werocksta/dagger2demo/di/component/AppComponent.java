package com.werocksta.dagger2demo.di.component;

import android.app.Application;

import com.werocksta.dagger2demo.di.module.AppModule;
import com.werocksta.dagger2demo.view.activity.MainActivity;
import com.werocksta.dagger2demo.view.fragment.MainFragment;
import com.werocksta.dagger2demo.view.fragment.RepoFragment;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainFragment fragment);

    void inject(MainActivity fragment);

    void inject(RepoFragment fragment);

    void inject(Application application);
}
