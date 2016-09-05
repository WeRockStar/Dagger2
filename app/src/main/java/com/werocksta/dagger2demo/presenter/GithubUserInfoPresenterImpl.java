package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.manager.ApiService;
import com.werocksta.dagger2demo.model.GithubUserInfoCollection;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class GithubUserInfoPresenterImpl implements GithubUserInfoPresenter {

    private GithubUserInfoPresenter.View view;
    private ApiService service;
    private CompositeSubscription subscription;

    public GithubUserInfoPresenterImpl(View view, ApiService service) {
        this.view = view;
        this.service = service;
        this.subscription = new CompositeSubscription();
    }

    @Override
    public void getUserInfo(String username) {
        view.loading();

        subscription.add(service.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable::error)
                .doOnTerminate(() -> view.getUserInfoComplete())
                .subscribe(
                        result -> view.getUserInfoSuccess(result),
                        t -> view.getUserInfoError(t.getMessage()),
                        () -> view.getUserInfoComplete())
        );
    }

    @Override
    public void onStop() {
        subscription.clear();
    }
}
