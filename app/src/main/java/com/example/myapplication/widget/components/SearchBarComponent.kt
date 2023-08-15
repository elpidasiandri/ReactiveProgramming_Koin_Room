package com.example.myapplication.widget.components

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.myapplication.R
import com.example.myapplication.databinding.SearchBarComponentBinding
import com.example.myapplication.utils.extensions.hide
import com.example.myapplication.utils.extensions.show
import com.example.myapplication.utils.extensions.textChanges
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchBarComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyleRes) {

    private val binding = SearchBarComponentBinding.inflate(LayoutInflater.from(context), this)
    private var searchModeToChangeIcons: Boolean = true

    fun cancelSearch(action: () -> Unit) {
        binding.apply {
            cancelSearch.setOnClickListener {
                searchEt.text.clear()
                searchModeToChangeIcons = false
                hideIcons()
                action()
            }
        }
    }
    fun cleanSearch(action: () -> Unit) {
        binding.apply {
            cleanSearch.setOnClickListener {
                searchEt.text.clear()
                searchModeToChangeIcons = true
                showIcons()
                action()
            }
        }
    }
    fun textChangesListener(timeOut: Long, lifecycleScope: LifecycleCoroutineScope, action: (CharSequence) -> Unit, actionWhenIsEmpty: () -> Unit) {
        binding.searchEt.textChanges().filterNot {
            actionWhenIsEmpty()
            hideIcons()
            it.isNullOrBlank()
        }.debounce(timeOut)
            .onEach { char ->
                char?.let {
                    action(it)
                    showIcons()
                }
            }
            .launchIn(lifecycleScope)
    }
    fun setHint(hint: String) {
        binding.searchEt.hint = hint
    }
        private fun showIcons() {
        binding.apply {
            cleanSearch.show()
            cancelSearch.show()
        }
    }
    private fun hideIcons() {
        binding.apply {
            cleanSearch.hide()
            cancelSearch.hide()
        }
    }
    fun getSearchText(): String = binding.searchEt.text.toString()
}