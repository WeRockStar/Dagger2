# Google Dagger 2

[![Build Status](https://travis-ci.org/WeRockStar/Dagger2.svg?branch=master)](https://travis-ci.org/WeRockStar/Dagger2) [![CircleCI](https://circleci.com/gh/WeRockStar/Dagger2/tree/master.svg?style=svg)](https://circleci.com/gh/WeRockStar/Dagger2/tree/master) [![codecov](https://codecov.io/gh/WeRockStar/Dagger2/branch/master/graph/badge.svg)](https://codecov.io/gh/WeRockStar/Dagger2)




### Fully static, compile-time dependency injection framework for both Java and Android.
#### Configuration
##### build.gradle project level

``` groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.1'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}
```

###### build.gradle application module level

``` groovy
apply plugin: 'com.neenbedankt.android-apt'

dependencies {
    ...
    compile 'com.google.dagger:dagger:2.7'
    apt 'com.google.dagger:dagger-compiler:2.7'
}
```