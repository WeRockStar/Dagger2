package com.werocksta.dagger2demo.di.module;

import android.app.Activity;
import android.content.Context;

import com.werocksta.dagger2demo.di.ActivityContext;
import com.werocksta.dagger2demo.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    @ActivityContext
    public Context provideContext() {
        return activity;
    }
}
