package com.example.newtineproject.data.remote.dto.article_detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetail(
    @SerialName("code")
    val code: Int,
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: ArticleDetailResult
)