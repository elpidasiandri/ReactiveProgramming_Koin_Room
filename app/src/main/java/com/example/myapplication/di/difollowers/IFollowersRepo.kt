package com.example.myapplication.di.difollowers

import com.example.myapplication.db.entinties.FollowersEntity
import kotlinx.coroutines.flow.Flow

interface IFollowersRepo {
    suspend fun getFollowersList(): List<FollowersEntity>
    suspend fun searchFollowers(searchString: String): List<FollowersEntity>
}