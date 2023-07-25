package com.example.myapplication.domain

data class UserRow(
    val id: Int = 0,
    val name: String?,
    val userName: String?,
    val avatarUrl: String?,
    val isVerifiedCreator: Boolean?,
    var hasFriendRequest: Boolean?,
    var status: String?,
    val isFriends: Boolean?,
    var friendRequestIsOut: Boolean?,
    var amIfollowing: Boolean = false,
    val lastName: String = "",
)
