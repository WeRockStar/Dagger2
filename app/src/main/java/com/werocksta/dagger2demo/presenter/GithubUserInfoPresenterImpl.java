package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.manager.ApiService;
import com.werocksta.dagger2demo.model.GithubUserInfoCollection;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
        service.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GithubUserInfoCollection>() {
                    @Override
                    public void call(GithubUserInfoCollection githubUserInfoCollection) {
                        view.getUserInfoComplete();
                        view.getUserInfoSuccess(githubUserInfoCollection);
                    }
                });
        ;
    }
}
