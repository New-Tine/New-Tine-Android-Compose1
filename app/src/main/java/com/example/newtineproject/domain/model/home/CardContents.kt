package com.example.newtineproject.domain.model.home

data class CardContents(
    val image: String,
    val buttonList: List<Category>,
    val newsTitle: String,
    val press: String,
    val date: String,
    val newsContent: String
)
