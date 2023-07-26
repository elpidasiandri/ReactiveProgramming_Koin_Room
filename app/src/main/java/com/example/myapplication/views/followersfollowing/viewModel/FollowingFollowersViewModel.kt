package com.example.myapplication.views.followersfollowing.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.LocalDatabase
import com.example.myapplication.domain.UserRow
import com.example.myapplication.utils.ViewState
import com.example.myapplication.di.diFollowing.FollowingImpl
import com.example.myapplication.di.difollowers.FollowersImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class FollowingFollowersViewModel(
    private val getFollowingImpl: FollowingImpl,
    private val getFollowersImpl: FollowersImpl,
) : ViewModel() {
    val uiState = ViewState(FollowingFollowersState())
    fun loadFollowers() {

        var followerList = emptyList<UserRow>()
        viewModelScope.launch {
            followerList = getFollowersImpl.getFollowersList().map {
                it.convertFollowersListToUser()
            }
            Log.d("Q12345", " loadFollowers followerList $followerList")
            uiState.updateState {
                it.copy(
                    followerslist = followerList,
                    EventName = if (followerList.isEmpty()) FollowingFollowersEvents.IsEmpty else FollowingFollowersEvents.LoadItems
                )
            }
        }
    }

    fun setEventNone() {
        uiState.updateState {
            it.copy(
                EventName = FollowingFollowersEvents.None
            )
        }
    }
    fun refreshFollowing(){
        uiState.updateState {
            it.copy(
                EventName =  FollowingFollowersEvents.Refresh
            )
        }
        loadFollowing()
    }
    fun refreshFollowers(){
        uiState.updateState {
            it.copy(
                EventName =  FollowingFollowersEvents.Refresh
            )
        }
        loadFollowers()
    }
    fun loadFollowing() {
        var followingList = emptyList<UserRow>()
        viewModelScope.launch {
            followingList = getFollowingImpl.getFollowingList().map {
                it.convertFollowingListToUser()
            }
            uiState.updateState {
                it.copy(
                    followingList = followingList,
                    EventName = if (followingList.isEmpty()) FollowingFollowersEvents.IsEmpty else FollowingFollowersEvents.LoadItems
                )
            }
        }
    }

    fun searchFollowers(searchString: String) {
        var searchFollowingList = emptyList<UserRow>()

        viewModelScope.launch {
            searchFollowingList = getFollowingImpl.searchFollowing(searchString).map {
                it.convertFollowingListToUser()
            }
            uiState.updateState {
                it.copy(
                    searchFollowingList = searchFollowingList,
                    searchMode = true,
                    EventName = if (searchFollowingList.isEmpty()) FollowingFollowersEvents.IsEmpty else FollowingFollowersEvents.LoadItems
                )
            }
        }
    }

    fun searchFollowing() {

    }
}