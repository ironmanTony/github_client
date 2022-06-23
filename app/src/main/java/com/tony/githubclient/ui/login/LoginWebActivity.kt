package com.tony.githubclient.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.activity.viewModels
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.just.agentweb.WebViewClient
import com.tony.githubclient.model.TokenParam
import com.tony.githubclient.ui.webview.WebViewActivity
import kotlin.random.Random

class LoginWebActivity : WebViewActivity() {

    companion object {
        const val redirectUrl = "https://tony.com/githubclient"
        const val client_id = "23985fd0ac4a1612feac"
        const val client_secret = "9e557f1f07a2cf6dcff26bcad5b724eb8bd823ed"

        fun startLogin(context: Context) {
            val uri = Uri.parse("https://github.com/login/oauth/authorize").buildUpon()
            uri.appendQueryParameter("client_id", client_id)
            uri.appendQueryParameter("state", Random.nextInt().toString())
            val intent = Intent(context, LoginWebActivity::class.java)
            intent.putExtra("url", uri.build().toString())
            context.startActivity(intent)
        }
    }

    private val mViewModel: LoginViewModel by viewModels()

    override fun getWebViewClient(): WebViewClient {
        return LoginWebViewClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.tokenLiveData.observe(this) {
            if (it) {
                // TODO: translate
                ToastUtils.showShort("登录成功")
                finish()
            } else {
                // TODO: translate
                ToastUtils.showShort("登录失败，请重试")
            }
        }
    }

    private fun checkAndLogin(code: String?) {
        if (code.isNullOrEmpty()) {
            //todo translate
            ToastUtils.showShort("Login error")
            return
        }
        val params = TokenParam(client_id, client_secret, code)
        mViewModel.accessToken(params)
    }

    inner class LoginWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = request?.url.toString()
            LogUtils.d("redirect url:$url")
            if (url.startsWith(redirectUrl)) {
                val uri = request?.url!!
                val code = uri.getQueryParameter("code")
                checkAndLogin(code)
                return true
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
}
