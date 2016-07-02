package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.model.GithubUserInfoCollection;

public interface GithubUserInfoPresenter {

    void getUserInfo(String username);

    interface View {
        void loading();
        
        void getUserInfoSuccess(GithubUserInfoCollection userInfo);

        void getUserInfoError(String message);

        void getUserInfoComplete();
    }
}
