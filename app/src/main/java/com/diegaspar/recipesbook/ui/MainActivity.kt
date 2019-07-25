package com.diegaspar.recipesbook.ui

import android.view.Menu
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import com.diegaspar.recipesbook.R
import com.diegaspar.recipesbook.base.BaseActivity
import com.diegaspar.recipesbook.base.BaseFragment

class MainActivity : BaseActivity() {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    override fun fragment(): BaseFragment = RecipesFragment()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        setupMenu(menu)
        return true
    }

    private fun setupMenu(menu: Menu?) {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.queryHint = getString(R.string.search_hint)
    }
}
