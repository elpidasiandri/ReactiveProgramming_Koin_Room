package com.example.myapplication.utils

import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.utils.extensions.subscribeToStateG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ViewState<T>(val init: T) {

    private val _state = MutableStateFlow(init)
    private val state: StateFlow<T> = _state

    fun updateState(lambda: (T) -> T) {
        _state.update { lambda(_state.value) }
    }

    fun reInitialiseState() {
        _state.update {
            init
        }
    }

    fun getStateCurrentValue(): T {
        return _state.value
    }

    fun subscribeToState(viewLifecycleOwner: LifecycleOwner, actOnEvents: (T) -> Unit) {
        subscribeToStateG<T>(state, viewLifecycleOwner) { actOnEvents(it) }
    }
}