package com.werocksta.dagger2demo.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werocksta.dagger2demo.R;

import butterknife.ButterKnife;

public class RepoFragment extends Fragment {


    public RepoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
