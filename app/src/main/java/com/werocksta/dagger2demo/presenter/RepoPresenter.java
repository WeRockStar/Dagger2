package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.model.RepoCollection;

public interface RepoPresenter {

    void getRepo(String user);

    public interface View {
        void displayRepo(RepoCollection repo);

        void getRepoError(String message);
    }
}
