package com.tony.githubclient.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.tony.githubclient.model.TokenParam
import com.tony.githubclient.net.getGitHubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginViewModel : ViewModel() {

    val tokenLiveData = MutableLiveData<Boolean>()

    fun accessToken(params: TokenParam) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = getGitHubApi().accessToken(params)
                    var loginSuccess = true
                    if (result.isSuccessful && result.body() != null) {
                        val token = result.body()
                        SPStaticUtils.put("token", token!!.access_token)
                    } else {
                        loginSuccess = false
                    }
                    tokenLiveData.postValue(loginSuccess)
                } catch (e: Exception) {
                    tokenLiveData.postValue(false)
                    LogUtils.e(e)
                }
            }
        }
    }
}