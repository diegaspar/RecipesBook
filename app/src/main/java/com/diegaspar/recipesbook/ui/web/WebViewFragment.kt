package com.diegaspar.recipesbook.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.annotation.LayoutRes
import com.diegaspar.recipesbook.R
import com.diegaspar.recipesbook.base.BaseFragment
import kotlinx.android.synthetic.main.webview_fragment.*

class WebViewFragment : BaseFragment() {

    @LayoutRes
    override fun getLayoutResId() = R.layout.webview_fragment

    lateinit var urlHref: String

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_webview.webChromeClient = WebChromeClient()
        fragment_webview.webViewClient = WebViewClient()
        fragment_webview.settings.userAgentString = "Mozilla/5.0 Google"
        CookieManager.getInstance().setAcceptCookie(true)
        fragment_webview.settings.setAppCacheEnabled(true)
        fragment_webview.settings.databaseEnabled = true
        fragment_webview.settings.domStorageEnabled = true
        fragment_webview.loadUrl(urlHref)
    }

    fun setUrl(url: String) {
        urlHref = url
    }

}