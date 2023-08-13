package com.example.myapplication.views.followersfollowing.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.entinties.FollowersEntity
import com.example.myapplication.db.entinties.FollowingEntity
import com.example.myapplication.di.diFollowing.FollowingImpl
import com.example.myapplication.di.difollowers.FollowersImpl
import com.example.myapplication.domain.UserRow
import com.example.myapplication.utils.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

internal class FollowingFollowersViewModel(
    private val getFollowingImpl: FollowingImpl,
    private val getFollowersImpl: FollowersImpl,
) : ViewModel() {

    val uiState = ViewState(FollowingFollowersState())
    var searchTerm = ""
    private val searchQueries = MutableSharedFlow<String>()

    val searchResultsFlowFollowing: Flow<List<FollowingEntity>> = searchQueries
        .flatMapLatest { query ->
            flow {
                val followingList = getFollowingImpl.searchFollowing(query)
                emit(followingList)
            }
        }
    val searchResultsFlowFollowers: Flow<List<FollowersEntity>> = searchQueries
        .flatMapLatest { query ->
            flow {
                val followersList = getFollowersImpl.searchFollowers(query)
                emit(followersList)
            }
        }

    init {
        viewModelScope.launch {
            if (searchTerm != "") {
                searchResultsFlowFollowing.collect { searchResults ->
                }
                searchResultsFlowFollowers.collect { searchResults ->
                }
            }
        }
    }

    fun loadFollowers() {
        var followerList = emptyList<UserRow>()
        viewModelScope.launch {
            followerList = getFollowersImpl.getFollowersList().map {
                it.convertFollowersListToUser()
            }
            uiState.updateState {
                it.copy(
                    followerslist = followerList,
                    EventName = if (followerList.isEmpty()) FollowingFollowersEvents.IsEmpty else FollowingFollowersEvents.LoadItemsFollowers
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

    fun onCancelSearchFollowing() {
        searchTerm = ""
        uiState.updateState {
            it.copy(
                EventName = FollowingFollowersEvents.CancelSearchFollowing
            )
        }
    }

    fun onCancelSearchFollowers() {
        searchTerm = ""
        uiState.updateState {
            it.copy(
                EventName = FollowingFollowersEvents.CancelSearchFollowers
            )
        }
    }

    fun onSearch(keyword: String) {
        if (searchTerm != keyword && !keyword.isNullOrBlank()) {
            searchTerm = keyword
            viewModelScope.launch {
                searchQueries.emit(keyword)
            }
        }
    }

    fun refreshSearch() {
        viewModelScope.launch {
            searchQueries.emit(searchTerm)
        }
    }

    fun refreshFollowing() {
        uiState.updateState {
            it.copy(
                EventName = FollowingFollowersEvents.Refresh
            )
        }
        loadFollowing()
    }

    fun refreshFollowers() {
        uiState.updateState {
            it.copy(
                EventName = FollowingFollowersEvents.Refresh
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
                    EventName = if (followingList.isEmpty()) FollowingFollowersEvents.IsEmpty else FollowingFollowersEvents.LoadItemsFollowing
                )
            }
        }
    }
}