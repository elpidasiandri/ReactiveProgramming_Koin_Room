package com.example.myapplication.db.entinties

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.FriendRequestDetails
import com.example.myapplication.domain.UserRow


@Entity(tableName = "following")
data class FollowingEntity(
    @PrimaryKey(autoGenerate = true)
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
) {
    fun convertFollowingListToUser(): UserRow {
        return UserRow(
            id = id,
            name = name,
            userName = userName,
            avatarUrl = avatarUrl,
            isVerifiedCreator = isVerifiedCreator,
            hasFriendRequest = hasFriendRequest,
            status = status,
            isFriends = isFriends,
            friendRequestIsOut = friendRequestIsOut,
            amIfollowing = amIfollowing,
            lastName = lastName
        )
    }
}