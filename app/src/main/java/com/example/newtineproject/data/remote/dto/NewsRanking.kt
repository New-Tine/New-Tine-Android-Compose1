package com.example.newtineproject.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsRanking(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)