package com.example.newtineproject.domain.model.article


data class Article(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val publication: String,
    val timePassed: Int,
    val contents:String,
    val category: String,
)