package com.example.newtineproject.ui.screens.notification

import com.example.newtineproject.domain.model.Notification

data class NotificationState(
    val notifications: List<Notification> = emptyList(),
    val error: String = ""
)
