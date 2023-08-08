package com.example.newtineproject.ui.screens.home.notification

import com.example.newtineproject.domain.model.notification.Notification

data class NotificationState(
    val notifications: List<Notification> = emptyList(),
    val error: String = ""
)
