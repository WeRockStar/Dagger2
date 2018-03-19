package com.werocksta.dagger2demo.presenter


import com.werockstar.dagger2demo.api.GithubAPI
import com.werockstar.dagger2demo.model.Repo
import com.werockstar.dagger2demo.presenter.RepoPresenter
import com.werockstar.dagger2demo.rx.RxThread
import com.werockstar.dagger2demo.util.RxScheduler
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*

class RepoPresenterTest {

    @Mock private lateinit var api: GithubAPI
    @Mock private lateinit var view: RepoPresenter.View

    private lateinit var presenter: RepoPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = RepoPresenter(api, RxScheduler.rxScheduler)
        presenter.injectView(view)
    }

    @Test
    @Throws(Exception::class)
    fun `get repo should display list repository`() {
        val user = "WeRockStar"
        val collections = ArrayList<Repo>()

        `when`(api.getRepo(user)).thenReturn(Observable.just(collections))
        presenter.getRepo(user)

        verify<RepoPresenter.View>(view).loading()
        verify<RepoPresenter.View>(view).displayRepo(collections)
        verify<RepoPresenter.View>(view).loadComplete()
    }

    @Test
    @Throws(Exception::class)
    fun `get repo error should return empty array`() {
        val emptyUser = ""

        `when`(api.getRepo(emptyUser)).thenReturn(Observable.error(Throwable()))
        presenter.getRepo(emptyUser)

        verify<RepoPresenter.View>(view).loading()
        verify<RepoPresenter.View>(view).displayRepo(ArrayList<Repo>())
        verify<RepoPresenter.View>(view).loadComplete()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.onStop()
    }
}