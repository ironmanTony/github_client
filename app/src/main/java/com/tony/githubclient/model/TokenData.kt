package com.tony.githubclient.model

import androidx.annotation.Keep

@Keep
data class TokenData(
    var access_token: String? = null,
    var scope: String? = null,
    var token_type: String? = null
)

@Keep
data class TokenParam(
    var client_id: String = "",
    var client_secret: String = "",
    var code: String = ""
)