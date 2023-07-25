package com.example.myapplication.di.difollowers

import com.example.myapplication.db.daos.FollowersDao
import com.example.myapplication.db.entinties.FollowersEntity

internal class FollowersImpl(
    private val dao: FollowersDao,
) : IFollowersRepo {
    override suspend fun getFollowersList(): List<FollowersEntity> {
        return dao.getFollowersList()
    }

    override suspend fun searchFollowers(searchString: String) {
        dao.searchFollowers(searchString)
    }
}