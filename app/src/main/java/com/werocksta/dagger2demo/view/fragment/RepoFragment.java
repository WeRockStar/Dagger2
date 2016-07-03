package com.werocksta.dagger2demo.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werocksta.dagger2demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoFragment extends Fragment {

    @BindView(R.id.rvList)
    RecyclerView rvList;

    public static RepoFragment newInstance(String user) {
        RepoFragment fragment = new RepoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("user", user);
        fragment.setArguments(bundle);
        return fragment;
    }

    public RepoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo, container, false);
        ButterKnife.bind(this, view);
        setUpView();
        return view;
    }

    private void setUpView() {

    }

}
