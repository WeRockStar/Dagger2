package com.werockstar.dagger2demo.view.activity

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.di.component.TestComponent
import com.werockstar.dagger2demo.toJsonString
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

    private val server by lazy { MockWebServer() }

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

    @Test
    fun user_not_found_should_no_see_username() {
        MockResponse().setResponseCode(404)

        activityRule.launchActivity(null)

        onView(withId(R.id.edtUsername))
                .perform(typeText(""))
        onView(withId(R.id.btnLoad))
                .perform(click())

        onView(withId(R.id.tvUsername))
                .check(matches(withText("")))
        onView(withId(R.id.tvRepo))
                .check(matches(withText("")))
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
                    "repos" -> MockResponse().setBody("repo.json".toJsonString())
                    else -> MockResponse()
                            .setResponseCode(200)
                            .setBody("github_profile.json".toJsonString())
                }
            }
        })
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
