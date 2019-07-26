package com.diegaspar.recipesbook.ui.web

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.diegaspar.recipesbook.base.BaseActivity
import com.diegaspar.recipesbook.base.BaseFragment


class WebViewActivity : BaseActivity() {

    private val webViewFragment = WebViewFragment()

    override fun fragment(): BaseFragment = webViewFragment

    @LayoutRes
    override fun getLayoutResId() = com.diegaspar.recipesbook.R.layout.activity_main

    companion object {

        const val KEY_URL = "key_url"
        fun createIntent(activity: Activity, url: String): Intent {

            val intent = Intent(activity, WebViewActivity::class.java)
            val bundle = Bundle()
            bundle.putString(KEY_URL, url)
            intent.putExtras(bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent.extras?.getString(KEY_URL, "google.com")?.let { webViewFragment.setUrl(it) }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}