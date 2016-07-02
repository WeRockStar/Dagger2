package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.manager.ApiService;
import com.werocksta.dagger2demo.model.GithubUserInfoCollection;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GithubUserInfoPresenterImpl implements GithubUserInfoPresenter {

    private GithubUserInfoPresenter.View view;
    private ApiService service;

    public GithubUserInfoPresenterImpl(View view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void getUserInfo(String username) {
        view.loading();
        
        service.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends GithubUserInfoCollection>>() {
                    @Override
                    public Observable<? extends GithubUserInfoCollection> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<GithubUserInfoCollection>() {
                    @Override
                    public void onCompleted() {
                        view.getUserInfoComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getUserInfoError(e.getMessage().toString());
                        view.getUserInfoComplete();
                    }

                    @Override
                    public void onNext(GithubUserInfoCollection githubUserInfoCollection) {
                        view.getUserInfoSuccess(githubUserInfoCollection);
                    }
                });
    }
}
