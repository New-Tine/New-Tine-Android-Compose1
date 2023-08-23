package com.example.newtineproject.data.remote.dto.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResult(
    @SerialName("newsId")
    val newsId: Int,
    @SerialName("imgUrl")
    val imgUrl: String,
    @SerialName("pressName")
    val pressName: String,
    @SerialName("pressImg")
    val pressImg: String,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
)