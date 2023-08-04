package com.example.newtineproject.domain.model.notification

import androidx.compose.ui.graphics.vector.ImageVector

data class Notification(
    val icon: ImageVector,
    val category: String,
    val timePassed: Int,
    val title: String
)
