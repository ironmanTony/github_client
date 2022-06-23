package com.tony.githubclient.net.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ChangeHostInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val path = chain.request().url.toUri().path
        return if (path == "login/oauth/access_token") {
            val request = chain.request().newBuilder()
            request.url("https://github.com/login/oauth/access_token")
            chain.proceed(request.build())
        } else {
            chain.proceed(chain.request())
        }
    }
}