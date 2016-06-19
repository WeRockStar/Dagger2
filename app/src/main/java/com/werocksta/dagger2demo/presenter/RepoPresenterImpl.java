package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.manager.ApiService;


public class RepoPresenterImpl implements RepoPresenter {

    RepoPresenter.View view;
    ApiService service;

    public RepoPresenterImpl(View view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void getRepo(String user) {

    }
}
