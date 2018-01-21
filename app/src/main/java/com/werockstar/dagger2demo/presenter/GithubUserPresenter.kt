package com.werockstar.dagger2demo.presenter

import com.werockstar.dagger2demo.api.GithubAPI
import com.werockstar.dagger2demo.model.GithubUser
import com.werockstar.dagger2demo.rx.RxThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class GithubUserPresenter @Inject constructor(private val api: GithubAPI,
                                              private val rxThread: RxThread) {

    private lateinit var view: View
    private val subscription = CompositeDisposable()

    interface View {
        fun loading()

        fun dismissLoading()

        fun getUserInfoSuccess(userInfo: GithubUser)

        fun getUserInfoError(message: String?)

        fun hideKeyboard()
    }

    fun injectView(view: View) {
        this.view = view
    }

    fun getUserInfo(username: String) {
        view.loading()
        subscription.add(api.getUser(username)
                .compose(rxThread.applyAsync())
                .doOnTerminate {
                    view.dismissLoading()
                    view.hideKeyboard()
                }
                .subscribe({
                    view.getUserInfoSuccess(it)
                }, {
                    view.getUserInfoError(it.message)
                })
        )
    }


    fun onDestroy() {
        subscription.clear()
    }
}
