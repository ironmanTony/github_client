package com.tony.githubclient.net.interceptors

import com.blankj.utilcode.util.SPStaticUtils
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SPStaticUtils.getString("token")
        return if (token.isNullOrEmpty().not()) {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "token $token")
                .build()
            chain.proceed(request)
        } else {
            chain.proceed(chain.request())
        }
    }
}