package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.manager.ApiService;
import com.werocksta.dagger2demo.model.RepoCollection;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class RepoPresenter {

    private View view;
    private ApiService service;
    private CompositeSubscription subscription;

    public interface View {
        void loading();
        void displayRepo(List<RepoCollection> repo);
        void getRepoError(String message);
        void loadComplete();
    }

    public RepoPresenter(View view, ApiService service) {
        this.view = view;
        this.service = service;
        this.subscription = new CompositeSubscription();
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
