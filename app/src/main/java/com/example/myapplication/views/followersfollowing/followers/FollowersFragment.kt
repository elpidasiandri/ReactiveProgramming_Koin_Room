package com.example.myapplication.views.followersfollowing.followers

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
import com.example.myapplication.utils.extensions.hide
import com.example.myapplication.views.followersfollowing.FollowingFollowersAdapter
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersEvents
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.ref.WeakReference

internal class FollowersFragment : Fragment(), IScrollListener {
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
        viewModel.loadFollowers()
        setAdapter()
        initUI()
        initViewModel()
    }

    private fun showSearchList() {
        binding.searchRefresher.show()
    }

    private fun hideSearchList() {
        binding.searchRefresher.hide()
    }

    private fun showFollowersList() {
        binding.listRefresher.show()
    }

    private fun hideFollowersList() {
        binding.listRefresher.hide()
    }

    private fun showEmptyResults() {
        hideFollowersList()
        hideSearchList()
        binding.emptyFollowersHolder.show()
    }

    private fun initViewModel() {
        viewModel.uiState.subscribeToState(viewLifecycleOwner) {
            when (it.EventName) {
                FollowingFollowersEvents.IsEmpty -> {
                    showEmptyResults()
                }

                FollowingFollowersEvents.LoadItemsFollowers -> {
                    showFollowersList()
                    hideSearchList()
                    binding.listRefresher.isRefreshing = false
                    adapter.submitList(it.followerslist.toSet().toList())
                }
                FollowingFollowersEvents.CancelSearchFollowers -> {
                    viewModel.setEventNone()
                    binding.apply {
                        showFollowersList()
                        hideSearchList()
                    }
                }
                else -> {}
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResultsFlowFollowers.collect { followersResults ->
                binding.searchRefresher.isRefreshing = false
                if (followersResults.isEmpty()) {
                    showEmptyResults()
                } else {
                    if (viewModel.searchTerm.isNullOrEmpty()){
                        showEmptyResults()
                    }
                    else{
                        showSearchList()
                        hideFollowersList()
                        val userRowList = followersResults.map { it.convertFollowersListToUser() }
                        searchAdapter.submitList(userRowList)
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        adapter = FollowingFollowersAdapter(
            scrollListener = WeakReference(this),
        )
        binding.listRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.listRecycler.adapter = adapter

        binding.searchRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.searchRecycler.adapter = searchAdapter
    }

    private fun initUI() {
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.apply {
            listRefresher.setOnRefreshListener {
                listRefresher.isRefreshing = true
                viewModel.refreshFollowers()
            }
            searchRefresher.setOnRefreshListener {
                searchRefresher.isRefreshing = true
                viewModel.refreshSearch()
            }
        }
    }

    override fun onScrolledToEnd() {
    }
}