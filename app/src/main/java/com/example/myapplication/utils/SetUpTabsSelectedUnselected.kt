package com.example.myapplication.utils

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout


object SetUpTabsSelectedUnselected {

    fun changeTabLayout(tab: TabLayout.Tab, isActive: Boolean) {
        val linearLayout = tab.customView as LinearLayout
        for (i in 0 until linearLayout.childCount) {
            val v: View = linearLayout.getChildAt(i)
            changeCustomTabStyle(v as TextView, isActive)
        }
    }

    private fun changeCustomTabStyle(textView: TextView, isBold: Boolean) {
        when (isBold) {
            true -> {
                textView.setTextColor(textView.context.getColor(R.color.new_black_opacity_90))
                textView.setTextAppearance(R.style.ButtonMediumFont)
            }
            else -> {
                textView.setTextColor(textView.context.getColor(R.color.secondary_text_colour))
                textView.setTextAppearance(R.style.MainTabTextStyle)
            }
        }
    }

    fun setColorsOfFeedTabs(tabLayout: TabLayout, isWallet: Boolean) {
        tabLayout.setBackgroundColor(ResourcesCompat.getColor(tabLayout.context.resources, R.color.transparent, null))

        for (i in 0 until 3) {
            when (isWallet) {
                true -> {
                    setTextColorForSpecificTab(tabLayout.getTabAt(i), R.color.black)
                }
                else -> {
                    tabLayout.setBackgroundColor(ResourcesCompat.getColor(tabLayout.context.resources, R.color.transparent, null))
                    setTextColorForSpecificTab(tabLayout.getTabAt(i), R.color.white)
                }
            }
        }
    }

    private fun setTextColorForSpecificTab(tab: TabLayout.Tab?, color: Int) {
        val linearLayout = tab?.customView as LinearLayout
        val v = linearLayout.getChildAt(0)
        val text = v as TextView
        text.setTextColor(text.context.getColor(color))
    }
}