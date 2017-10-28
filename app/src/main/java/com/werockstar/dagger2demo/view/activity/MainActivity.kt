package com.werockstar.dagger2demo.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.werockstar.dagger2demo.MainApplication
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.di.component.ActivityComponent
import com.werockstar.dagger2demo.di.component.DaggerActivityComponent
import com.werockstar.dagger2demo.di.module.ActivityModule
import com.werockstar.dagger2demo.view.fragment.MainFragment
import com.werockstar.dagger2demo.view.fragment.RepoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent((application as MainApplication).component)
                .build()

        setContentView(R.layout.activity_main)

        initialFragment()
    }

    private fun initialFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, MainFragment())
                .commit()
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.contentContainer, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun getComponent() = component

    fun onClickRepoList(user: String) {
        changeFragment(RepoFragment.newInstance(user))
    }
}
