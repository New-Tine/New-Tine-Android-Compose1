package com.example.newtineproject.ui.screens.home.search


import com.example.newtineproject.data.remote.dto.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchState(
    @SerialName("code")
    val code: Int = 200,
    @SerialName("isSuccess")
    val isSuccess: Boolean = false,
    @SerialName("message")
    val message: String = "",
    @SerialName("result")
    val result: List<Result> = emptyList()
)