package com.example.myapplication.modules

import com.example.myapplication.db.LocalDatabase
import com.example.myapplication.di.diFollowing.FollowingImpl
import com.example.myapplication.di.diFollowing.IFollowingRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val followingModule = module(createdAtStart = true) {
    single { LocalDatabase.newInstance(androidContext()) }
    single { get<LocalDatabase>().followingListDao() }
    single<IFollowingRepo> {
        FollowingImpl(get())
    }

}