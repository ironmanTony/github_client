package com.tony.githubclient.net

import com.tony.githubclient.net.interceptors.ChangeHostInterceptor
import com.tony.githubclient.net.interceptors.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val retrofit: Retrofit by lazy {
    val client = OkHttpClient.Builder()
        .callTimeout(40L, TimeUnit.SECONDS)
        .connectTimeout(20L, TimeUnit.SECONDS)
        .addInterceptor(TokenInterceptor())
        .addInterceptor(ChangeHostInterceptor())
        .build()
    Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getGitHubApi(): GitHubApi {
    return retrofit.create(GitHubApi::class.java)
}