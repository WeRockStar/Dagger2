package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.model.RepoCollection;

import java.util.List;

public interface RepoPresenter {

    void getRepo(String user);

    void onStop();

    interface View {
        void loading();

        void displayRepo(List<RepoCollection> repo);

        void getRepoError(String message);

        void loadComplete();
    }
}
