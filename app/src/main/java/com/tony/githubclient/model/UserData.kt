package com.tony.githubclient.model

import androidx.annotation.Keep

@Keep
data class UserData(
    var name: String = "",
    var email: String = "",
    var company: String = "",
    var blog: String = "",
    var location: String = "",
)