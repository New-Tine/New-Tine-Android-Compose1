package com.example.newtineproject.ui.screens.categoryNews

import com.example.newtineproject.domain.model.CategoryNews

data class CategoryNewsState(
    val categoryNews: List<CategoryNews> = emptyList(),
    val error: String = ""
)