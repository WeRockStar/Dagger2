package com.werocksta.dagger2demo.presenter

import com.werocksta.dagger2demo.api.GithubAPI
import com.werocksta.dagger2demo.model.RepoCollection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


open class RepoPresenter @Inject
constructor(private val api: GithubAPI) {

    private lateinit var view: View
    private val subscription = CompositeDisposable()

    interface View {
        fun loading()

        fun displayRepo(repos: List<RepoCollection>)

        fun loadComplete()
    }

    fun injectView(view: View) {
        this.view = view
    }

    fun getRepo(user: String) {
        view.loading()
        subscription.add(api.getRepo(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { view.loadComplete() }
                .onErrorReturnItem(ArrayList<RepoCollection>())
                .subscribe({ repo -> view.displayRepo(repo) }
                ))
    }


    fun onStop() {
        subscription.clear()
    }
}
