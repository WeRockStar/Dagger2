package com.werockstar.dagger2demo

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner


class MockTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, MockTestRunner::class.java.name, context)
    }
}