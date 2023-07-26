package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.db.entinties.FollowersEntity

@Dao
interface FollowersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(followerUser: FollowersEntity)

    @Query("SELECT * FROM followers")
    suspend fun getFollowersList(): List<FollowersEntity>

    @Query("SELECT * FROM followers WHERE name LIKE '%' || :searchString || '%' OR userName LIKE '%' || :searchString || '%'")
    suspend fun searchFollowers(searchString: String): List<FollowersEntity>
}
