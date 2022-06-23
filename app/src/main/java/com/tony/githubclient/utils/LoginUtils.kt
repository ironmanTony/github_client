package com.tony.githubclient.utils

import com.blankj.utilcode.util.SPStaticUtils

object LoginUtils {

    fun isLogin(): Boolean {
        return SPStaticUtils.getString("token").isNullOrEmpty().not()
    }


}