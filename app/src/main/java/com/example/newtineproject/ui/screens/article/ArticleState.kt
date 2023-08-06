package com.example.newtineproject.ui.screens.article

import com.example.newtineproject.domain.model.Article

data class ArticleState(
    val categoryNews: List<Article> = emptyList(),
    val error: String = ""
)