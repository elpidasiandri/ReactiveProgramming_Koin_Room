package com.example.myapplication.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendRequestDetails(
    val influencerId: String?,
    val friendRequestId: String?,
    val status: String?,
    val timestamp: String?,
    val timestampInMilliseconds: String?,
    val isOut: Boolean?,
)

object FriendStatus {
    const val NOT_REQUESTED = "not_requested"
    const val PENDING = "requested"
    const val OUT_FRIEND_REQUEST = "outFriendRequest"
    const val FRIENDS = "friends"
    const val DECLINED = "declined"
}