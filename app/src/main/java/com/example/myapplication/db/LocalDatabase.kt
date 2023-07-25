package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.db.daos.FollowersDao
import com.example.myapplication.db.daos.FollowingDao
import com.example.myapplication.db.entinties.FollowersEntity
import com.example.myapplication.db.entinties.FollowingEntity

@Database(entities = [FollowersEntity::class, FollowingEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun followersListDao(): FollowersDao
    abstract fun followingListDao(): FollowingDao

    companion object {
        private const val DB_NAME = "database"
        fun newInstance(context: Context) =
            Room.databaseBuilder(context, LocalDatabase::class.java, DB_NAME).build()
    }
}