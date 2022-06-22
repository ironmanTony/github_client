package com.tony.githubclient.ui.home.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.tony.githubclient.base.BaseFragment
import com.tony.githubclient.databinding.FragmentTrendingBinding
import com.tony.githubclient.ui.webview.WebViewActivity

class TrendingFragment : BaseFragment<FragmentTrendingBinding>() {

    companion object {
        const val TRENDING_URL = "https://github.com/trending"
    }

    override fun getDataBindingClass(): Class<FragmentTrendingBinding> {
        return FragmentTrendingBinding::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.agentWebview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                // open in webviewActivity
                request?.url?.toString()?.let {
                    WebViewActivity.start(requireContext(), it)
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        mBinding.agentWebview.loadUrl(TRENDING_URL)
    }

    override fun onPause() {
        mBinding.agentWebview.onPause()
        super.onPause()
    }

    override fun onResume() {
        mBinding.agentWebview.onResume()
        super.onResume()
    }

    override fun onDestroyView() {
        mBinding.agentWebview.destroy()
        super.onDestroyView()
    }
}