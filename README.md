# Google Dagger 2

[![Build Status](https://travis-ci.org/WeRockStar/Dagger2.svg?branch=master)](https://travis-ci.org/WeRockStar/Dagger2) [![codecov](https://codecov.io/gh/WeRockStar/Dagger2/branch/master/graph/badge.svg)](https://codecov.io/gh/WeRockStar/Dagger2)




### Fully static, compile-time dependency injection framework for both Java and Android.
#### Configuration
##### build.gradle project level

``` groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.2'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}
```

###### build.gradle application module level

``` groovy
apply plugin: 'com.neenbedankt.android-apt'

dependencies {
    ...
    compile 'com.google.dagger:dagger:2.8'
    apt 'com.google.dagger:dagger-compiler:2.8'
}
```