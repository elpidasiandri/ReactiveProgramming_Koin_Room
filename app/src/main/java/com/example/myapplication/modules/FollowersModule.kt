package com.example.myapplication.modules

import com.example.myapplication.db.LocalDatabase
import com.example.myapplication.di.difollowers.FollowersImpl
import com.example.myapplication.di.difollowers.IFollowersRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val followersModule = module(createdAtStart = true) {
    single { LocalDatabase.newInstance(androidContext()) }
    single { get<LocalDatabase>().followersListDao() }
    single<IFollowersRepo> {
        FollowersImpl(get())
    }
}