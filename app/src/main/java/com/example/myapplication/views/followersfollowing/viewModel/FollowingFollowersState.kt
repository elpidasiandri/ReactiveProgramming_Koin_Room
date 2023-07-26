package com.example.myapplication.views.followersfollowing.viewModel

import com.example.myapplication.domain.UserRow

data class FollowingFollowersState(
    val EventName: FollowingFollowersEvents = FollowingFollowersEvents.None,
    val followerslist:List<UserRow> = emptyList(),
    val followingList:List<UserRow> = emptyList(),
    val searchFollowingList:List<UserRow> = emptyList(),
    val searchMode: Boolean = false,
)

enum class FollowingFollowersEvents {
    None,
    LoadItems,
    LoadMore,
    Refresh,
    IsEmpty,
    SearchItems,
    SearchLoadMore
}