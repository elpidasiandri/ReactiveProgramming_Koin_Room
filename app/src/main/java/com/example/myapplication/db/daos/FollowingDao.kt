package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.db.entinties.FollowingEntity

@Dao
interface FollowingDao {
    @Insert
    suspend fun insert(followingUser: FollowingEntity)

    @Query("SELECT * FROM following")
    suspend fun getFollowingList(): List<FollowingEntity>

    @Query("SELECT * FROM following WHERE name LIKE '%' || :searchString || '%' OR userName LIKE '%' || :searchString || '%'")
    suspend fun searchFollowing(searchString: String): List<FollowingEntity>
}