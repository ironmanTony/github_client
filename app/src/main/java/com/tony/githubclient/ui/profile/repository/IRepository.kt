package com.tony.githubclient.ui.profile.repository

import com.tony.githubclient.model.UserData

interface IRepository {
    fun fetchData():UserData?
}