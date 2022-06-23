package com.tony.githubclient.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.tony.githubclient.model.UserData
import com.tony.githubclient.net.getGitHubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProfileViewModel : ViewModel() {

    var mUserInfo: UserData? = null

    val mUserLiveData = MutableLiveData<Boolean>()

    fun getUserInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val userRes = getGitHubApi().user()
                    if (userRes.isSuccessful) {
                        mUserInfo = userRes.body()
                        mUserLiveData.postValue(true)
                    }
                } catch (e: Exception) {
                    LogUtils.e(e)
                }
            }
        }

    }
}