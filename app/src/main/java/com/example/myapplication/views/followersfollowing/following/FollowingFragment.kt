package com.example.myapplication.views.followersfollowing.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FFollowersFollowingBinding
import com.example.myapplication.utils.extensions.show
import com.example.myapplication.utils.IScrollListener
import com.example.myapplication.utils.Interactors
import com.example.myapplication.views.followersfollowing.FollowingFollowersAdapter
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersEvents
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersViewModel
import org.koin.android.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.ref.WeakReference

internal class FollowingFragment : Fragment(), IScrollListener {
    private lateinit var binding: FFollowersFollowingBinding
    private val searchAdapter = FollowingFollowersAdapter(
        WeakReference(this)
    )
    private var adapter = FollowingFollowersAdapter(
        WeakReference(this)
    )
    private val viewModel: FollowingFollowersViewModel by  sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FFollowersFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.loadFollowing()
        initUI()
        initViewModel()
    }

    override fun onDestroy() {
        Interactors.glide.clearMemoryCache()
        super.onDestroy()
    }
    private fun initViewModel() {
        viewModel.uiState.subscribeToState(viewLifecycleOwner){
            when(it.EventName){
                FollowingFollowersEvents.IsEmpty ->{
                    binding.emptyFollowersHolder.show()
                }
                FollowingFollowersEvents.LoadItems ->{
                    adapter.submitList(it.followingList)
                }
                else ->{}
            }
        }
    }

    private fun initUI() {

    }

    override fun onScrolledToEnd() {
        TODO("Not yet implemented")
    }
}