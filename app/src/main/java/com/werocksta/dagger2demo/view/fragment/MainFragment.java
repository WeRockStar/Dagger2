package com.werocksta.dagger2demo.view.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.werocksta.dagger2demo.MainApplication;
import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.api.GithubAPI;
import com.werocksta.dagger2demo.model.GithubUserCollection;
import com.werocksta.dagger2demo.presenter.GithubUserPresenter;
import com.werocksta.dagger2demo.util.KeyboardUtil;
import com.werocksta.dagger2demo.view.activity.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment implements GithubUserPresenter.View {

    @Inject
    GithubAPI service;


    @Inject
    CustomTabsIntent intent;

    @BindView(R.id.edtUsername)
    EditText edtUsername;

    @BindView(R.id.ivProfile)
    ImageView ivProfile;

    @BindView(R.id.tvUsername)
    TextView tvUsername;

    @BindView(R.id.tvRepo)
    TextView tvRepo;

    @Inject
    Context mContext;

    @Inject
    GithubUserPresenter presenter;

    ProgressDialog progressDialog;

    public MainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((MainApplication) getActivity().getApplication()).getComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        presenter.injectView(this);
        progressDialog = new ProgressDialog(getContext());
        return view;
    }

    @OnClick(R.id.btnLoad)
    void onClickLoadUserInfo() {
        presenter.getUserInfo(edtUsername.getText().toString());
    }

    @OnClick(R.id.rootLayout)
    void hideKeyboard() {
        (new KeyboardUtil()).hideKeyboard(edtUsername, mContext);
    }

    @Override
    public void loading() {
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    public void getUserInfoSuccess(final GithubUserCollection userInfo) {
        tvUsername.setText(userInfo.getUsername());
        tvRepo.setClickable(true);
        tvRepo.setText(userInfo.getRepoUrl());
        tvRepo.setOnClickListener(view -> ((MainActivity) getActivity()).onClickRepoList(userInfo.getUsername()));

        displayAvatarImage(userInfo.getImageUrl());
    }

    private void displayAvatarImage(String avatarUrl) {
        Glide.with(this).load(avatarUrl).diskCacheStrategy(DiskCacheStrategy.RESULT).into(ivProfile);
    }

    @Override
    public void getUserInfoError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserInfoComplete() {
        progressDialog.cancel();
        (new KeyboardUtil()).hideKeyboard(edtUsername, mContext);
    }

    @Override
    public void onStop() {
        super.onStop();

        presenter.onStop();
    }

}
