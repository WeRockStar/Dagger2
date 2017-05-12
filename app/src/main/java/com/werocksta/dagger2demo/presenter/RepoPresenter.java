package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.api.GithubAPI;
import com.werocksta.dagger2demo.model.RepoCollection;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class RepoPresenter {

    private View view;
    private GithubAPI service;
    private final CompositeSubscription subscription = new CompositeSubscription();

    public interface View {
        void loading();

        void displayRepo(List<RepoCollection> repo);

        void getRepoError(String message);

        void loadComplete();
    }

    @Inject
    public RepoPresenter(GithubAPI service) {
        this.service = service;
    }

    public void injectView(View view) {
        this.view = view;
    }

    public void getRepo(String user) {
        view.loading();

        subscription.add(service.getRepo(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable::error)
                .doOnTerminate(() -> view.loadComplete())
                .subscribe(
                        repo -> view.displayRepo(repo),
                        throwable -> view.getRepoError(throwable.getMessage())
                ));
    }


    public void onStop() {
        subscription.clear();
    }
}
