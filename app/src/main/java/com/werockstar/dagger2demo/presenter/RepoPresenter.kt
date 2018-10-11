package com.werockstar.dagger2demo.presenter

import com.werockstar.dagger2demo.rx.RxThread
import com.werockstar.dagger2demo.api.GithubAPI
import com.werockstar.dagger2demo.model.Repo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


open class RepoPresenter @Inject
constructor(private val api: GithubAPI, private val rxThread: RxThread) {

    private lateinit var view: View
    private val disposable = CompositeDisposable()

    interface View : BaseView {
        fun displayRepo(repos: List<Repo>)
    }

    fun injectView(view: View) {
        this.view = view
    }

    fun getRepo(user: String) {
        view.loading()
        disposable.add(api.getRepo(user)
                .compose(rxThread.applyAsync())
                .doOnTerminate { view.dismissLoading() }
                .onErrorReturnItem(emptyList())
                .subscribe { view.displayRepo(it) }
        )
    }


    fun onStop() {
        disposable.clear()
    }
}
