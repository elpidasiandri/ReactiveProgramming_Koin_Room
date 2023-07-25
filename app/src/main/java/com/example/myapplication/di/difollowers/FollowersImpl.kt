package com.example.myapplication.di.difollowers

import com.example.myapplication.db.daos.FollowersDao

internal class FollowersImpl(
    private val dao: FollowersDao,
) : IFollowersRepo {
    override suspend fun getFollowersList() {
        dao.getFollowersList()
    }

    override suspend fun searchFollowers(searchString: String) {
        dao.searchFollowers(searchString)
    }
}