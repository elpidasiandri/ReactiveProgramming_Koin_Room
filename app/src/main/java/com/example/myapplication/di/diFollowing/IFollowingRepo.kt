package com.example.myapplication.di.diFollowing

import com.example.myapplication.db.entinties.FollowingEntity

interface IFollowingRepo {
    suspend fun getFollowingList(): List<FollowingEntity>
    suspend fun searchFollowing(searchString:String)
}