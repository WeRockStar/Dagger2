package com.werocksta.dagger2demo.presenter

import com.werocksta.dagger2demo.api.GithubAPI
import com.werocksta.dagger2demo.model.GithubUserCollection

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class GithubUserPresenter @Inject constructor(private val api: GithubAPI) {

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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { view.getUserInfoComplete() }
                .subscribe(
                        { gitUserInfo ->
                            view.getUserInfoSuccess(gitUserInfo)
                        }
                ) { throwable ->
                    view.getUserInfoError(throwable.message)
                }
        )
    }


    fun onStop() {
        subscription.clear()
    }
}
