package com.werocksta.dagger2demo.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.werocksta.dagger2demo.MainApplication;
import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.manager.ApiService;
import com.werocksta.dagger2demo.model.GithubUserInfoCollection;
import com.werocksta.dagger2demo.model.RepoCollection;
import com.werocksta.dagger2demo.presenter.GithubUserInfoPresenter;
import com.werocksta.dagger2demo.presenter.GithubUserInfoPresenterImpl;
import com.werocksta.dagger2demo.presenter.RepoPresenter;
import com.werocksta.dagger2demo.presenter.RepoPresenterImpl;

import javax.inject.Inject;

import butterknife.BindView;

public class MainFragment extends Fragment implements GithubUserInfoPresenter.View {

    @Inject
    ApiService service;

    @BindView(R.id.edtUsername)
    EditText edtUsername;

    @BindView(R.id.btnLoad)
    Button btnLoad;

    GithubUserInfoPresenter presenter;

    public MainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((MainApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        presenter = new GithubUserInfoPresenterImpl(this, service);
        return view;
    }

    @Override
    public void getUserInfoSuccess(GithubUserInfoCollection userInfo) {

    }

    @Override
    public void getUserInfoError(String message) {

    }

    @Override
    public void getUserInfoComplete() {

    }
}
