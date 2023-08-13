package com.example.myapplication.views.followersfollowing.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.tools.build.jetifier.core.utils.Log
import com.example.myapplication.databinding.FFollowersFollowingBinding
import com.example.myapplication.utils.extensions.show
import com.example.myapplication.utils.IScrollListener
import com.example.myapplication.utils.Interactors
import com.example.myapplication.utils.extensions.hide
import com.example.myapplication.views.followersfollowing.FollowingFollowersAdapter
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersEvents
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersViewModel
import kotlinx.coroutines.launch
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
    private val viewModel: FollowingFollowersViewModel by sharedViewModel()

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
        initUI()
        initViewModel()
    }

    override fun onDestroy() {
        Interactors.glide.clearMemoryCache()
        super.onDestroy()
    }

    private fun initViewModel() {
        viewModel.uiState.subscribeToState(viewLifecycleOwner) {
            when (it.EventName) {
                FollowingFollowersEvents.IsEmpty -> {
                    showEmptyResults()
                }
                FollowingFollowersEvents.LoadItemsFollowing -> {
                    showFollowingList()
                    binding.listRefresher.isRefreshing = false
                    adapter.submitList(it.followingList)
                }
                FollowingFollowersEvents.CancelSearchFollowing -> {
                    viewModel.setEventNone()
                    binding.apply {
                        showFollowingList()
                        hideSearchList()
                    }
                }
                else -> {}
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResultsFlowFollowing.collect { followingResults ->
                binding.searchRefresher.isRefreshing = false
                if (followingResults.isEmpty()) {
                    showEmptyResults()
                } else {
                    showSearchList()
                    hideFollowingList()
                    val userRowList = followingResults.map { it.convertFollowingListToUser() }
                    searchAdapter.submitList(userRowList)
                }
            }
        }

    }

    private fun showEmptyResults() {
        hideFollowingList()
        hideSearchList()
        binding.emptyFollowersHolder.show()
    }

    private fun hideSearchList() {
        binding.searchRefresher.hide()
    }

    private fun showSearchList() {
        binding.searchRefresher.show()
    }

    private fun hideFollowingList() {
        binding.listRefresher.hide()
    }

    private fun showFollowingList() {
        binding.listRefresher.show()
    }

    private fun initUI() {
        setAdapters()
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.apply {
            listRefresher.setOnRefreshListener {
                listRefresher.isRefreshing = true
                viewModel.refreshFollowing()
            }
            searchRefresher.setOnRefreshListener {
                searchRefresher.isRefreshing = true
                  viewModel.refreshSearch()
            }
        }
    }

    private fun setAdapters() {
        binding.listRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.listRecycler.adapter = adapter
        binding.searchRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.searchRecycler.adapter = searchAdapter
    }

    override fun onScrolledToEnd() {
    }
}