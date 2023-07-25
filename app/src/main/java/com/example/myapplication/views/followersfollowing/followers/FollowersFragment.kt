package com.example.myapplication.views.followersfollowing.followers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FFollowersFollowingBinding
import com.example.myapplication.db.LocalDatabase
import com.example.myapplication.utils.extensions.show
import com.example.myapplication.utils.IScrollListener
import com.example.myapplication.views.followersfollowing.FollowingFollowersAdapter
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersEvents
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersViewModel
import kotlinx.coroutines.GlobalScope
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

    private fun initViewModel() {
        viewModel.uiState.subscribeToState(viewLifecycleOwner) {
            Log.d("Q12345 ", "it.EventName ${it.EventName}")
            when (it.EventName) {
                FollowingFollowersEvents.IsEmpty -> {
                    binding.emptyFollowersHolder.show()
                }
                FollowingFollowersEvents.LoadItems -> {
                    binding.listRefresher.show()
                    adapter.submitList(it.followerslist)
                    viewModel.setEventNone()
                }

                else -> {}
            }
        }
    }

    private fun setAdapter() {
        adapter = FollowingFollowersAdapter(
            scrollListener = WeakReference(this),
        )
        binding.listRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.listRecycler.adapter = adapter
    }

    private fun initUI() {
    }

    override fun onScrolledToEnd() {
    }
}