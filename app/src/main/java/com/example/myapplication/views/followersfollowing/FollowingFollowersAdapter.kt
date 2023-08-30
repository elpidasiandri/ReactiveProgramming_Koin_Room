package com.example.myapplication.views.followersfollowing

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.tools.build.jetifier.core.utils.Log
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.domain.FriendStatus
import com.example.myapplication.domain.UserRow
import com.example.myapplication.utils.extensions.hide
import com.example.myapplication.utils.extensions.show
import com.example.myapplication.utils.IScrollListener
import com.example.myapplication.widget.RoundedImageView
import java.lang.ref.WeakReference

internal open class FollowingFollowersAdapter(
    private val scrollListener: WeakReference<IScrollListener>,
) :
    ListAdapter<UserRow, FollowingFollowersAdapter.FollowingFollowersViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<UserRow>() {

        override fun areItemsTheSame(oldItem: UserRow, newItem: UserRow) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserRow, newItem: UserRow) =
            oldItem == newItem
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        submitList(null)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FollowingFollowersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FollowingFollowersViewHolder(inflater.inflate(R.layout.li_friend, parent, false))
    }

    override fun getItemCount(): Int = currentList.size

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: FollowingFollowersViewHolder, position: Int) {
        val result = currentList[position]
        val context = holder.itemView.context

        if (result.isVerifiedCreator != null && result.isVerifiedCreator != false) {
            holder.iconHalo.background =
                AppCompatResources.getDrawable(context, R.drawable.sm_avatar_halo)
        } else {
            holder.iconHalo.background =
                AppCompatResources.getDrawable(context, R.drawable.bg_avatar)
        }

        holder.friendName.text = result.name
        holder.friendUsername.text = result.userName ?: ""

        if (result.avatarUrl.isNullOrBlank()) {
            Glide.with(context)
                .load(R.drawable.ic_placeholder_face)
                .into(holder.avatar)
        } else {
            Glide.with(context)
                .load(result.avatarUrl)
                .into(holder.avatar)
        }
        when (result.status) {
            FriendStatus.FRIENDS -> {
                holder.areFriends.show()
                holder.addButton.hide()
                holder.pendingTv.hide()
            }

            FriendStatus.NOT_REQUESTED -> {
                val isFriends = result.isFriends ?: false
                if (isFriends) {
                    holder.areFriends.show()
                    holder.addButton.hide()
                    holder.pendingTv.hide()
                } else {
                    holder.areFriends.hide()
                    holder.addButton.show()
                    holder.pendingTv.hide()
                }
            }

            FriendStatus.PENDING -> {
                holder.areFriends.hide()
                holder.addButton.hide()
                holder.pendingTv.show()
            }

            else -> {
                holder.areFriends.hide()
                holder.addButton.show()
                holder.pendingTv.hide()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: List<UserRow>) {
        submitList(data)
    }

    internal inner class FollowingFollowersViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var container: LinearLayout = itemView.findViewById(R.id.container)
        var avatar: RoundedImageView = itemView.findViewById(R.id.searchAvatarIv)
        var waveView: ImageView = itemView.findViewById(R.id.waveIv)
        var moreActions: ImageView = itemView.findViewById(R.id.moreActionsButton)
        var friendName: TextView = itemView.findViewById(R.id.friendName)
        var friendUsername: TextView = itemView.findViewById(R.id.friendUsername)
        var iconHalo: FrameLayout = itemView.findViewById(R.id.avatarFrame)
        var pendingTv: TextView = itemView.findViewById(R.id.pendingTv)
        var areFriends: TextView = itemView.findViewById(R.id.sentIv)
        var addButton: TextView = itemView.findViewById(R.id.addButton)
        var buttonHolder: LinearLayout = itemView.findViewById(R.id.buttonHolder)
    }
}