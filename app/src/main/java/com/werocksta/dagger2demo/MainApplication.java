package com.werocksta.dagger2demo;

import android.app.Application;

import com.werocksta.dagger2demo.di.component.AppComponent;
import com.werocksta.dagger2demo.di.component.DaggerAppComponent;

public class MainApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}

