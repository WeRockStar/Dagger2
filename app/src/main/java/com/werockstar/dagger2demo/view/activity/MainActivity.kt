package com.werockstar.dagger2demo.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.werockstar.dagger2demo.R
import com.werockstar.dagger2demo.view.fragment.MainFragment
import com.werockstar.dagger2demo.view.fragment.RepoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    fun onClickRepoList(user: String) {
        changeFragment(RepoFragment.newInstance(user))
    }
}
