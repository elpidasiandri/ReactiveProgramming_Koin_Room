package com.example.myapplication.views.followersfollowing.viewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication.db.LocalDatabase
import com.example.myapplication.domain.UserRow
import com.example.myapplication.utils.ViewState
import com.example.myapplication.di.diFollowing.FollowingImpl
import com.example.myapplication.di.difollowers.FollowersImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class FollowingFollowersViewModel(
    private val getFollowingImpl : FollowingImpl,
    private val getFollowersImpl : FollowersImpl,
    ) : ViewModel() {
    val uiState = ViewState(FollowingFollowersState())
//
//    fun loadFollowers() {
//        var followerList = emptyList<UserRow>()
//        GlobalScope.launch {
//            followerList = localDatabase.followersListDao().getFollowersList().map {
//                it.convertFollowersListToUser()
//            }
//
//        }
//
//        uiState.updateState {
//            it.copy(
//                followerslist = followerList,
//                EventName = if (followerList.isEmpty()) FollowingFollowersEvents.IsEmpty else FollowingFollowersEvents.LoadItems
//            )
//        }
//    }
//
//    fun loadFollowing() {
//        var followingList = emptyList<UserRow>()
//        GlobalScope.launch {
//            followingList = localDatabase.followingListDao().getFollowingList().map {
//                it.convertFollowingListToUser()
//            }
//
//        }
//
//        uiState.updateState {
//            it.copy(
//                followingList = followingList,
//                EventName = if (followingList.isEmpty()) FollowingFollowersEvents.IsEmpty else FollowingFollowersEvents.LoadItems
//            )
//        }
//    }

    fun searchFollowers() {

    }

    fun searchFollowing() {

    }
}