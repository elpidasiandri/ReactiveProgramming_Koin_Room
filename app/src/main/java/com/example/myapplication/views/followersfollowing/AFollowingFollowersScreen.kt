package com.example.myapplication.views.followersfollowing

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.android.tools.build.jetifier.core.utils.Log
import com.example.myapplication.R
import com.example.myapplication.utils.activities.BaseActivity
import com.example.myapplication.databinding.FollowingFollowersScreenBinding
import com.example.myapplication.utils.FormatUtils
import com.example.myapplication.utils.SetUpTabsSelectedUnselected
import com.example.myapplication.views.followersfollowing.followers.FollowersFragment
import com.example.myapplication.views.followersfollowing.following.FollowingFragment
import com.example.myapplication.views.followersfollowing.viewModel.FollowingFollowersViewModel
import com.example.myapplication.views.followersfollowing.viewModel.di.followingFollowersScreenModule
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AFollowingFollowersScreen : BaseActivity() {
    private lateinit var binding: FollowingFollowersScreenBinding
    private var activeTab = 0
    private val viewModel: FollowingFollowersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FollowingFollowersScreenBinding.inflate(layoutInflater)
        adjustFontScale(resources.configuration)
        setContentView(binding.root)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
        loadKoinModules(followingFollowersScreenModule)
        viewModel.loadFollowing()
        setUpAdapterTab()
        setUpUi()
        setUpClickListeners()
    }

    override fun onDestroy() {
        unloadKoinModules(followingFollowersScreenModule)
        super.onDestroy()
    }
    private fun setUpClickListeners() {
        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        }
        onBackPressedDispatcher?.addCallback(this, backPressedCallback)
        binding.buttonBack.setOnClickListener {
            finishAffinity()
        }
    }

    private fun setUpUi() {
        setUpSearchBarComponent()
    }

    private fun setUpSearchBarComponent() {
        binding.searchBarComponent.apply {
            val timerForSearch: Long = 400
            textChangesListener(
                timerForSearch,
                lifecycleScope,
                { text ->
                    search(text.toString())
                },
                {}
            )

            cancelSearch {
                cancelSearch()
                hideKeyboard()
            }

            cleanSearch {
                cancelSearch()
            }
        }
    }

    private fun cancelSearch() {
            viewModel.onCancelSearchFollowing()
            viewModel.onCancelSearchFollowers()
    }

    private fun setUpAdapterTab() {
        binding.pager.adapter = ScreenSlidePagerAdapter(this)
        binding.pager.currentItem = 0
        binding.pager.offscreenPageLimit = 2

        TabLayoutMediator(binding.followingFollowersTab, binding.pager) { tab, position ->

            tab.text = when (position) {
                1 -> getString(
                    R.string.followers_text,
                    FormatUtils.formatNumberForViewing(0)
                )

                else -> {
                    getString(
                        R.string.following_text,
                        FormatUtils.formatNumberForViewing(0)
                    )
                }
            }
        }.attach()

        val tab = binding.followingFollowersTab.getTabAt(0)
        val tab1 = binding.followingFollowersTab.getTabAt(1)

        tab?.setCustomView(R.layout.custom_tab_following)
        tab1?.setCustomView(R.layout.custom_tab_followers)
        tab?.select()

        setAllCaps(binding.followingFollowersTab, false)
        binding.pager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                hideKeyboard()
                if (position == 0) {
                    tabUi(R.string.find_following, 0)
                } else {
                    tabUi(R.string.find_followers_search, 1)
                }
                if (binding.searchBarComponent.getSearchText().isNotBlank()) {
                    search(binding.searchBarComponent.getSearchText())
                }
            }
        })
        binding.pager.currentItem = activeTab
    }

    @SuppressLint("CheckResult")
    private fun search(keyword: String) {
        if (keyword.isNotEmpty()) {
            viewModel.onSearch(keyword)
        } else viewModel.onCancelSearchFollowing()
    }

    private fun tabUi(text: Int, activeTabIndex: Int) {
        binding.searchBarComponent.setHint(getString(text))
        val tab = binding.followingFollowersTab.getTabAt(0)!!
        val tab1 = binding.followingFollowersTab.getTabAt(1)!!
        if (activeTabIndex == 0) {
            SetUpTabsSelectedUnselected.changeTabLayout(tab, true)
            SetUpTabsSelectedUnselected.changeTabLayout(tab1, false)
        } else {
            SetUpTabsSelectedUnselected.changeTabLayout(tab, false)
            SetUpTabsSelectedUnselected.changeTabLayout(tab1, true)
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                1 -> FollowersFragment()
                else -> FollowingFragment()
            }
        }
    }

    private fun setAllCaps(view: View?, caps: Boolean) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) setAllCaps(view.getChildAt(i), caps)
        } else if (view is TextView) view.isAllCaps = caps
    }

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, AFollowingFollowersScreen::class.java)
    }
}
