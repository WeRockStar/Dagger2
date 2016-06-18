package com.werocksta.dagger2demo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.werocksta.dagger2demo.MainApplication;
import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.manager.ApiService;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((MainApplication) getApplication()).getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (service != null)
            Log.d("Dagger", "Hello Dagger");
    }
}
