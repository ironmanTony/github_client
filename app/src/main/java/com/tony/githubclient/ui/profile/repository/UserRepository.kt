package com.tony.githubclient.ui.profile.repository

import com.tony.githubclient.model.UserData

class UserRepository : IRepository {
    private val db = DbRepository()
    private val net = NetRepository()


    override fun fetchData(): UserData? {
        val dbData = db.fetchData()
        return if (dbData != null) {
            dbData
        } else {
            val netData = net.fetchData()
            if (netData != null) {
                db.storeData(netData)
            }
            netData
        }

    }
}