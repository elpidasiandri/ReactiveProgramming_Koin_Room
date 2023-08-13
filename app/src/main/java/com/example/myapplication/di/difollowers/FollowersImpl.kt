package com.example.myapplication.di.difollowers

import com.example.myapplication.db.daos.FollowersDao
import com.example.myapplication.db.entinties.FollowersEntity
import com.example.myapplication.db.entinties.FollowingEntity

internal class FollowersImpl(
    private val dao: FollowersDao,
) : IFollowersRepo {
    override suspend fun getFollowersList(): List<FollowersEntity> {
        return dao.getFollowersList()
    }

    override suspend fun searchFollowers(searchString: String): List<FollowersEntity>  {
        return dao.searchFollowers(searchString)
    }
}