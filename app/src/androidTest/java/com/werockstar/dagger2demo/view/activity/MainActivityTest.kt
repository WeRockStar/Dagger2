package com.werockstar.dagger2demo.view.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.di.*
import com.werockstar.dagger2demo.di.component.DiComponent
import dagger.Component
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Singleton


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Singleton
    @Component(modules = arrayOf(MockHttpModule::class,
            MockActivityModule::class,
            MockAndroidModule::class,
            MockRxThreadModule::class,
            MockApplicationModule::class
    ))
    interface TestComponent : DiComponent {
        fun inject(activity: MainActivityTest)
    }

    @get:Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @Before
    fun setUp() {
        val instrument = InstrumentationRegistry.getInstrumentation()
        val app = instrument.targetContext.applicationContext as MainApplication
        val component = app.component as TestComponent
        component.inject(this)
    }

    @Test
    fun launchActivity() {
        activityRule.launchActivity(null)

        onView(withId(R.id.btnLoad))
                .check(matches(withText("Load")))
                .check(matches(isClickable()))
    }
}