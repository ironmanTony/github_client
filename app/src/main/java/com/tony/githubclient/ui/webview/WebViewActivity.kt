package com.tony.githubclient.ui.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebViewClient
import com.tony.githubclient.base.BaseActivity

open class WebViewActivity : BaseActivity() {

    companion object {
        fun start(context: Context, url: String?) {
            if (url.isNullOrEmpty()) {
                return
            }
            val intent = Intent(context, WebViewActivity::class.java).apply {
                putExtra("url", url)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var mWebView: AgentWeb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("url")
        if (url.isNullOrEmpty()) {
            this.finish()
            return
        }

        val content = LinearLayout(this)
        content.orientation = LinearLayout.VERTICAL

        mWebView = AgentWeb.with(this)
            .setAgentWebParent(content, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .apply { getWebViewClient()?.let { setWebViewClient(it) } }
            .createAgentWeb()
            .ready()
            .go(url)
        setContentView(content, FrameLayout.LayoutParams(-1, -1))
    }

    override fun onPause() {
        super.onPause()
        mWebView.webLifeCycle.onPause()
    }

    open fun getWebViewClient(): WebViewClient? {
        return null
    }

    override fun onResume() {
        super.onResume()
        mWebView.webLifeCycle.onResume()
    }

    override fun onDestroy() {
        mWebView.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (mWebView.handleKeyEvent(keyCode, event)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}