package com.example.myapplication.views.followersfollowing.viewModel.di

import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val followingFollowersScreenModule = module{

    viewModel {
        FollowingFollowersViewModel(get(),get())
    }
}