package ru.alexpanov.composetalk.ui.bottomsheet

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BottomSheetViewModel : ViewModel() {
    private val _text = MutableStateFlow("Hello world")

    val text: StateFlow<String>
        get() = _text.asStateFlow()

    init {
        println("MainViewModel: init")
    }

    override fun onCleared() {
        super.onCleared()

        println("MainViewModel: onCleared")
    }
}