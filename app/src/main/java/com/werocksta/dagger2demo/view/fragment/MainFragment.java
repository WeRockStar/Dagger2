package com.werocksta.dagger2demo.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werocksta.dagger2demo.MainApplication;
import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.manager.ApiService;

import javax.inject.Inject;

public class MainFragment extends Fragment {

    @Inject
    ApiService service;

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
        if (service != null)
            Log.d("Dagger", "Dagger well");
        return view;
    }

}
