package com.werockstar.dagger2demo.view.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.werockstar.dagger2demo.LoadJsonResource
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.di.component.TestComponent
import com.werockstar.dagger2demo.util.URL
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val server = MockWebServer()

    @get:Rule val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @Before
    fun setUp() {
        server.start(4000)
        URL.BASE_URL = server.url("/").toString()

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

        onView(withId(R.id.progressBar))
                .check(matches(not(isDisplayed())))
    }

    @Test
    fun type_user_we_rock_star_should_see_WeRockStar_and_repo_url() {
        activityRule.launchActivity(null)

        typeUserWeRockStar()
    }

    @Test
    fun type_user_we_rock_star_and_click_on_repo_url_should_see_list_of_repo() {
        activityRule.launchActivity(null)

        typeUserWeRockStar()

        onView(withId(R.id.tvRepo)).perform(click())
        onView(withId(R.id.smootProgressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
    }

    private fun typeUserWeRockStar() {
        val username = "WeRockStar"
        val repoUrl = "https://api.github.com/users/WeRockStar/repos"

        setUpMockResponse()

        onView(withId(R.id.edtUsername))
                .perform(typeText(username))

        onView(withId(R.id.btnLoad))
                .perform(click())

        onView(withId(R.id.tvUsername))
                .check(matches(withText(username)))

        onView(withId(R.id.tvRepo))
                .check(matches(withText(repoUrl)))
    }

    private fun setUpMockResponse() {
        server.setDispatcher(object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                val path = request?.path?.split("/")
                val endpoint = path?.last()
                return when (endpoint) {
                    "repos" -> MockResponse().setBody(LoadJsonResource.fromResource("repo.json"))
                    else -> MockResponse()
                            .setResponseCode(200)
                            .setBody(LoadJsonResource.fromResource("github_profile.json"))
                }
            }
        })
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}