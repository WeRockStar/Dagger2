package com.werockstar.dagger2demo.view.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.di.component.DiComponent
import com.werockstar.dagger2demo.di.module.AndroidModule
import com.werockstar.dagger2demo.di.module.ApplicationModule
import com.werockstar.dagger2demo.di.module.HttpModule
import com.werockstar.dagger2demo.di.module.RxThreadModule
import dagger.Component
import okhttp3.OkHttpClient
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Singleton


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Singleton
    @Component(modules = arrayOf(HttpModule::class,
            ApplicationModule::class, AndroidModule::class,
            RxThreadModule::class))
    interface TestComponent : DiComponent {
        fun inject(activity: MainActivityTest)
    }

    @Inject lateinit var okHttp: OkHttpClient

    @get:Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, true)

    @Before
    fun setUp() {
        val instrument = InstrumentationRegistry.getInstrumentation()
        val app = instrument.targetContext.applicationContext as MainApplication
        val component = app.component as TestComponent
        component.inject(this)
    }

    @Test
    fun launchActivity() {
        onView(withId(R.id.btnLoad))
                .check(matches(withText("Load")))
                .check(matches(isClickable()))

        onView(withId(R.id.progressBar))
                .check(matches(not(isDisplayed())))
    }

    @Test
    fun type_user_we_rock_star_should_see_WeRockStar_and_repo_url() {
        val idling = registerIdleResource("searchUser")
        typeUserWeRockStar()
        unregisterIdleResource(idling)
    }

    @Test
    fun type_user_we_rock_star_and_click_on_repo_url_should_see_list_of_repo() {
        val idling = registerIdleResource("clickRepo")

        typeUserWeRockStar()

        onView(withId(R.id.tvRepo)).perform(click())
        onView(withId(R.id.smootProgressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))

        unregisterIdleResource(idling)
    }

    private fun typeUserWeRockStar() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("WeRockStar"))

        onView(withId(R.id.btnLoad))
                .perform(click())

        onView(withId(R.id.tvUsername))
                .check(matches(withText("WeRockStar")))

        onView(withId(R.id.tvRepo))
                .check(matches(withText("https://api.github.com/users/WeRockStar/repos")))
    }

    private fun unregisterIdleResource(idlingResource: OkHttp3IdlingResource) {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    private fun registerIdleResource(idleName: String): OkHttp3IdlingResource {
        val idling = OkHttp3IdlingResource.create(idleName, okHttp)
        IdlingRegistry.getInstance().register(idling)
        return idling
    }
}