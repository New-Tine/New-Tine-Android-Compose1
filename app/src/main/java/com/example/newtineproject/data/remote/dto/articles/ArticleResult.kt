package com.example.newtineproject.data.remote.dto.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResult(
    @SerialName("imgUrl")
    val imgUrl: String,
    @SerialName("pressName")
    val pressName: String,
    @SerialName("title")
    val title: String
)