package com.diegaspar.recipesbook.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.diegaspar.recipesbook.R
import com.diegaspar.recipesbook.base.BaseActivity
import com.diegaspar.recipesbook.base.BaseFragment

class FavouritesActivity : BaseActivity(true) {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    override fun fragment(): BaseFragment = FavouritesFragment()

    companion object {

        fun createIntent(activity: Activity): Intent {

            return Intent(activity, FavouritesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.favourite_activity_title)
    }
}