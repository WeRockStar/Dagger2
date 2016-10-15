package com.werocksta.dagger2demo.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
                .activityModule(new ActivityModule())
                .build();

        setContentView(R.layout.activity_main);
        changeFragment(new MainFragment(), false);
    }

    private void changeFragment(Fragment fragment, boolean hasAddBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, fragment);
        if (hasAddBackStack) {
            transaction.addToBackStack(null).commit();
            return;
        }
        transaction.commit();
    }

    public void onClickRepoList(String user) {
        changeFragment(RepoFragment.newInstance(user), true);
    }

    public ActivityComponent getComponent() {
        return component;
    }
}
