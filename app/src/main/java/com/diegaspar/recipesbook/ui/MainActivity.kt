package com.diegaspar.recipesbook.ui

import androidx.annotation.LayoutRes
import com.diegaspar.recipesbook.R
import com.diegaspar.recipesbook.base.BaseActivity
import com.diegaspar.recipesbook.base.BaseFragment

class MainActivity : BaseActivity() {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    override fun fragment(): BaseFragment = RecipesFragment()
}
