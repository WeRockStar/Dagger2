package com.werocksta.dagger2demo.di;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ActivityContext
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
