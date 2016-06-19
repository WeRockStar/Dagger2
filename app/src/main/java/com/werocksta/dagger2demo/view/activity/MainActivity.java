package com.werocksta.dagger2demo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.view.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentContainer, new MainFragment())
                .commit();
    }
}
