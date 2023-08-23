package com.example.newtineproject.data.remote.dto.article_detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetailResult(
    @SerialName("category")
    val category: String,
    @SerialName("content")
    val content: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("pressImage")
    val pressImage: String,
    @SerialName("pressName")
    val pressName: String,
    @SerialName("pressSubscriber")
    val pressSubscriber: Int,
    @SerialName("scrapped")
    val scrapped: Boolean,
    @SerialName("subscribed")
    val subscribed: Boolean,
    @SerialName("newsImg")
    val newsImg: String,
    @SerialName("title")
    val title: String,
    @SerialName("newsId")
    val newsID: Long
)