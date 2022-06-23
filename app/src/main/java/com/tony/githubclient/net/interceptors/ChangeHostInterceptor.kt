package com.tony.githubclient.net.interceptors

import android.net.Uri
import okhttp3.Interceptor
import okhttp3.Response

class ChangeHostInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val path = chain.request().url.toUri().path
        return if (path == "/login/oauth/access_token") {
            val request = chain.request().newBuilder()
            val newUrl = Uri.parse(chain.request().url.toUri().toString())
                .buildUpon()
                .authority("github.com")
            request.url(newUrl.build().toString())
            chain.proceed(request.build())
        } else {
            chain.proceed(chain.request())
        }
    }
}