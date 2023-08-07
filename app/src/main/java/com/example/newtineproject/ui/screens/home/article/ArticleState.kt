package com.example.newtineproject.ui.screens.home.article

import com.example.newtineproject.domain.model.article.Article

data class ArticleState(
    val categoryNews: List<Article> = emptyList(),
    val error: String = ""
)