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


public class RepoPresenterImpl implements RepoPresenter {

    RepoPresenter.View view;
    ApiService service;

    CompositeSubscription subscription;

    public RepoPresenterImpl(View view, ApiService service) {
        this.view = view;
        this.service = service;
        this.subscription = new CompositeSubscription();
    }

    @Override
    public void getRepo(String user) {
        view.loading();

        subscription.add(service.getRepo(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<RepoCollection>>>() {
                    @Override
                    public Observable<? extends List<RepoCollection>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<RepoCollection>>() {
                    @Override
                    public void onCompleted() {
                        view.loadComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.loadComplete();
                        view.getRepoError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<RepoCollection> repoCollections) {
                        view.displayRepo(repoCollections);
                    }
                }));
    }

    @Override
    public void onStop() {
        subscription.clear();
    }
}
