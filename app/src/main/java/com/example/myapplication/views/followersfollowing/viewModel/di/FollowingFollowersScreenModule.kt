package com.example.myapplication.views.followersfollowing.viewModel.di

import com.example.myapplication.di.diFollowing.FollowingImpl
import com.example.myapplication.di.difollowers.FollowersImpl
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val followingFollowersScreenModule = module {

    viewModel {
        FollowingFollowersViewModel(get(), get())
    }

    factory { FollowingImpl(get()) }
    factory { FollowersImpl(get()) }
}