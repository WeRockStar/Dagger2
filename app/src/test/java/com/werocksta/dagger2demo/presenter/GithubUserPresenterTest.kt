package com.werocksta.dagger2demo.presenter

import com.werocksta.dagger2demo.api.GithubAPI
import com.werocksta.dagger2demo.model.GithubUserCollection
import com.werocksta.dagger2demo.util.RxSchedulersOverrideRule
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GithubUserPresenterTest {

    @Rule
    var mRxSchedulersOverride = RxSchedulersOverrideRule()
    @Mock lateinit var view: GithubUserPresenter.View
    @Mock lateinit var api: GithubAPI

    lateinit var presenter: GithubUserPresenter

    private val USER_NAME = "WeRockStar"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = GithubUserPresenter(api)
        presenter.injectView(view)
    }

    @Test
    @Throws(Exception::class)
    fun presenterShouldBeNotNull() {
        assertNotNull(presenter)
    }

    @Test
    @Throws(Exception::class)
    fun getUserInfoShouldHaveUserInfo() {
        val userInfo = GithubUserCollection()
        `when`(api.getUser(USER_NAME)).thenReturn(Observable.just(userInfo))
        presenter.getUserInfo(USER_NAME)
        verify(view).loading()
        verify(view).getUserInfoSuccess(userInfo)
        verify(view).getUserInfoComplete()
    }

    @Test
    @Throws(Exception::class)
    fun getUserInfoErrorShouldHaveException() {
        val exception = Throwable()
        `when`(api.getUser(USER_NAME)).thenReturn(Observable.error<GithubUserCollection>(exception))
        presenter.getUserInfo(USER_NAME)
        verify(view).loading()
        verify(view).getUserInfoError(exception.message)
        verify(view).getUserInfoComplete()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.onStop()
    }
}