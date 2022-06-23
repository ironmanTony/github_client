package com.tony.githubclient.net

import com.tony.githubclient.model.TokenData
import com.tony.githubclient.model.TokenParam
import com.tony.githubclient.model.UserData
import retrofit2.Response
import retrofit2.http.*

interface GitHubApi {

    @Headers("Accept:application/json")
    @POST("/login/oauth/access_token")
    suspend fun accessToken(@Body param: TokenParam): Response<TokenData?>

    @GET("/user")
    suspend fun user(): Response<UserData>

}