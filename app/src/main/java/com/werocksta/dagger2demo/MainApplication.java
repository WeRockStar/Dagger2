package com.werocksta.dagger2demo;

import android.app.Application;

import com.werocksta.dagger2demo.di.component.AppComponent;
import com.werocksta.dagger2demo.di.component.DaggerAppComponent;
import com.werocksta.dagger2demo.di.module.HttpModule;

public class MainApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .httpModule(new HttpModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}

