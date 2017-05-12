package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.api.GithubAPI;
import com.werocksta.dagger2demo.model.GithubUserCollection;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class GithubUserPresenter {

    private View view;
    private GithubAPI api;
    private final CompositeSubscription subscription = new CompositeSubscription();

    public interface View {
        void loading();

        void getUserInfoSuccess(GithubUserCollection userInfo);

        void getUserInfoError(String message);

        void getUserInfoComplete();
    }

    @Inject
    public GithubUserPresenter(GithubAPI service) {
        this.api = service;
    }

    public void injectView(View view) {
        this.view = view;
    }

    public void getUserInfo(String username) {
        view.loading();

        subscription.add(api.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> view.getUserInfoComplete())
                .subscribe(
                        gitUserInfo -> {
                            view.getUserInfoSuccess(gitUserInfo);
                        },
                        throwable -> {
                            view.getUserInfoError(throwable.getMessage());
                        }
                )
        );
    }


    public void onStop() {
        subscription.clear();
    }
}
