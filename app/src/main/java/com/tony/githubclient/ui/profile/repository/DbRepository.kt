package com.tony.githubclient.ui.profile.repository

import com.tony.githubclient.model.UserData

class DbRepository : IRepository {
    override fun fetchData():UserData? {
        //fetch from db
        return null;
    }

    fun storeData(data: UserData) {

    }

}