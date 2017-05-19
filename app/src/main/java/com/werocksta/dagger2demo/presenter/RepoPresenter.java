package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.api.GithubAPI;
import com.werocksta.dagger2demo.model.RepoCollection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class RepoPresenter {

    private View view;
    private GithubAPI api;
    private final CompositeDisposable subscription = new CompositeDisposable();

    public interface View {
        void loading();

        void displayRepo(List<RepoCollection> repo);

        void getRepoError(String message);

        void loadComplete();
    }

    @Inject
    public RepoPresenter(GithubAPI service) {
        this.api = service;
    }

    public void injectView(View view) {
        this.view = view;
    }

    public void getRepo(String user) {
        view.loading();

        subscription.add(api.getRepo(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> view.loadComplete())
                .onErrorReturnItem(new ArrayList<>())
                .subscribe(
                        repo -> view.displayRepo(repo),
                        throwable -> view.getRepoError(throwable.getMessage())
                ));
    }


    public void onStop() {
        subscription.clear();
    }
}
