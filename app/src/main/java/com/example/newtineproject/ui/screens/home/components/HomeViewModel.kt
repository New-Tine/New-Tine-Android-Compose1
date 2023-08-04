package com.example.newtineproject.ui.screens.home.components

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.newtineproject.ui.theme.BookMarkOff
import com.example.newtineproject.ui.theme.LightBlue

class HomeViewModel : ViewModel() {
    private val toggleStates = mutableStateListOf<Boolean>()

    init {
        // Initialize the list with default values
        toggleStates.addAll(tmpList.map { false })
    }

    fun getToggleState(index: Int): Boolean {
        return toggleStates[index]
    }

    fun setToggleState(index: Int, checked: Boolean) {
        toggleStates[index] = checked
    }

    fun getCurrentColor(index: Int): Color {
        return if (getToggleState(index)) LightBlue else BookMarkOff
    }
}