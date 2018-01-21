# Google Dagger 2 (Demo)

[![Build Status](https://travis-ci.org/WeRockStar/Dagger2.svg?branch=master)](https://travis-ci.org/WeRockStar/Dagger2) [![CircleCI](https://circleci.com/gh/WeRockStar/Dagger2/tree/master.svg?style=svg)](https://circleci.com/gh/WeRockStar/Dagger2/tree/master) [![codecov](https://codecov.io/gh/WeRockStar/Dagger2/branch/master/graph/badge.svg)](https://codecov.io/gh/WeRockStar/Dagger2)

### This project drive by Testing :) 

### Fully static, compile-time dependency injection framework for both Java and Android.
#### Configuration

###### build.gradle application module level

``` groovy
dependencies {
    ...
    implementation 'com.google.dagger:dagger:2.9'
    kapt 'com.google.dagger:dagger-compiler:2.9'
}
```

### You can use libraries or tools.
- RxJava2, RxAndroid2 
- Retrofit, OkHttp, OkHttp logging 
- Gson
- Dagger 2 
- ButterKnife 

#### Testing framework 
- Junit 
- Mockito
- Jacoco (Test coverage)
- Espresso (UI Testing from `Google`)

#### CI Tools
- Circle CI 
- Travis CI

### Run ui-test
```shell
./gralew connectAndroidTest
```
<p align="left">
    <img width="3¬00" src="ui-testing.gif" />
</p>