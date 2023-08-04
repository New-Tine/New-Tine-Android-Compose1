package com.example.newtineproject.ui.screens.article

import com.example.newtineproject.domain.model.CategoryNews

data class ArticleState(
    val categoryNews: List<CategoryNews> = emptyList(),
    val error: String = ""
)