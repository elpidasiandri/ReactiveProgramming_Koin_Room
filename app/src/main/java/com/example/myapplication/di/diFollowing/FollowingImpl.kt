package com.example.myapplication.di.diFollowing

import com.example.myapplication.db.daos.FollowingDao
import com.example.myapplication.db.entinties.FollowingEntity

internal class FollowingImpl(
    private val dao: FollowingDao,
) : IFollowingRepo {
    override suspend fun getFollowingList(): List<FollowingEntity> {
       return dao.getFollowingList()
    }

    override suspend fun searchFollowing(searchString: String) : List<FollowingEntity>{
       return  dao.searchFollowing(searchString)
    }
}