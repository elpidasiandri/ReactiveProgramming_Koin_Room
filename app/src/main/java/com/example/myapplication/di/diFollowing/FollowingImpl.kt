package com.example.myapplication.di.diFollowing

import com.example.myapplication.db.daos.FollowingDao

internal class FollowingImpl(
    private val dao: FollowingDao,
) : IFollowingRepo {
    override suspend fun getFollowingList() {
        dao.getFollowingList()
    }

    override suspend fun searchFollowing(searchString: String) {
        dao.searchFollowing(searchString)
    }
}