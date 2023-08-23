package com.example.newtineproject.data.remote.dto.news_ranking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsRanking(
    @SerialName("code")
    val code: Int,
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: List<Result>
)