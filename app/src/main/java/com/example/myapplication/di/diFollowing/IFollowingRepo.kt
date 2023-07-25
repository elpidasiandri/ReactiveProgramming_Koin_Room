package com.example.myapplication.di.diFollowing

interface IFollowingRepo {
    suspend fun getFollowingList()
    suspend fun searchFollowing(searchString:String)
}