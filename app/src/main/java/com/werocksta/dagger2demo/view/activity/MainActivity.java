package com.werocksta.dagger2demo.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.werocksta.dagger2demo.MainApplication;
import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.di.component.ActivityComponent;
import com.werocksta.dagger2demo.di.component.DaggerActivityComponent;
import com.werocksta.dagger2demo.di.module.ActivityModule;
import com.werocksta.dagger2demo.view.fragment.MainFragment;
import com.werocksta.dagger2demo.view.fragment.RepoFragment;

public class MainActivity extends AppCompatActivity {

    ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(((MainApplication) getApplication()).getComponent())
                .build();

        setContentView(R.layout.activity_main);
        changeFragment(new MainFragment());
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void onClickRepoList(String user) {
        changeFragment(RepoFragment.newInstance(user));
    }

    public ActivityComponent getComponent() {
        return component;
    }
}
