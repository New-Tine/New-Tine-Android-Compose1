package com.example.newtineproject.ui.screens.notification.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtineproject.domain.model.Notification

@Composable
fun NotificationItem(
    notification: Notification,
    onItemClick: (Notification) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(notification) }
            .padding(horizontal = 17.dp)
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = notification.icon,
                    contentDescription = "icon",
                    tint = Color(0xFF0094FF)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = notification.category,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                )
            }
            Text(
                text = notification.timePassed.toString() + "시간 전",
                style = TextStyle(
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = notification.title,
            style = TextStyle(
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.height(14.dp))
        Divider()
    }
}