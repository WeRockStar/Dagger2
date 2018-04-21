package com.werockstar.dagger2demo.presenter


import com.werockstar.dagger2demo.api.GithubAPI
import com.werockstar.dagger2demo.model.Repo
import com.werockstar.dagger2demo.util.RxScheduler
import io.reactivex.Observable
import org.junit.After
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

        verify(view).loading()
        verify(view).displayRepo(collections)
        verify(view).loadComplete()
    }

    @Test
    @Throws(Exception::class)
    fun `get repo error should return empty array`() {
        val emptyUser = ""

        `when`(api.getRepo(emptyUser)).thenReturn(Observable.error(Throwable()))
        presenter.getRepo(emptyUser)

        verify(view).loading()
        verify(view).displayRepo(ArrayList<Repo>())
        verify(view).loadComplete()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.onStop()
    }
}