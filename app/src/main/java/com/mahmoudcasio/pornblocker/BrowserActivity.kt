package com.mahmoudcasio.pornblocker

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class BrowserActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var address: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        webView = findViewById(R.id.webView)
        address = findViewById(R.id.address)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                val url = request.url.toString()
                if (DomainMatcher.isBlocked(url)) {
                    startActivity(Intent(this@BrowserActivity, BlockedActivity::class.java))
                    return true
                }
                return false
            }
        }
        address.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                var url = address.text.toString().trim()
                if (url.isNotEmpty()) {
                    if (!url.contains("://")) url = "https://$url"
                    if (DomainMatcher.isBlocked(url)) startActivity(Intent(this, BlockedActivity::class.java))
                    else webView.loadUrl(url)
                }
                true
            } else false
        }
        webView.loadUrl("https://www.google.com")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }
}
