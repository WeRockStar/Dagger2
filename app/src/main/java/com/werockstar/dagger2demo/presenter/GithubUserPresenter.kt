package com.werockstar.dagger2demo.presenter

import com.werockstar.dagger2demo.api.GithubAPI
import com.werockstar.dagger2demo.model.GithubUserCollection
import com.werockstar.dagger2demo.rx.RxThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class GithubUserPresenter @Inject constructor(private val api: GithubAPI,
                                              private val rxThread: RxThread) {

    private lateinit var view: View
    private val subscription = CompositeDisposable()

    interface View {
        fun loading()

        fun getUserInfoSuccess(userInfo: GithubUserCollection)

        fun getUserInfoError(message: String?)

        fun getUserInfoComplete()
    }

    fun injectView(view: View) {
        this.view = view
    }

    fun getUserInfo(username: String) {
        view.loading()
        subscription.add(api.getUser(username)
                .compose(rxThread.applyAsync())
                .doOnTerminate { view.getUserInfoComplete() }
                .subscribe({
                    view.getUserInfoSuccess(it)
                }, {
                    view.getUserInfoError(it.message)
                })
        )
    }


    fun onStop() {
        subscription.clear()
    }
}
