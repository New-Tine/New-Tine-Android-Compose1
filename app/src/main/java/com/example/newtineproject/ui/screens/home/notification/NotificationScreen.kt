package com.example.newtineproject.ui.screens.home.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newtineproject.domain.model.notification.Notification
import com.example.newtineproject.ui.screens.home.notification.components.NotificationItem
import com.example.newtineproject.ui.theme.LightBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                title = { Text(text = "알림") },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = LightBlue
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            contentPadding = it
        ) {
            items(notificationList) { notification ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    NotificationItem(
                        notification = notification,
                        onItemClick = { }
                    )
                }
            }
        }
    }
}

val notificationList = listOf(
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 2,
        title = "테슬라, 첫 전기 트럭 생산 소식에 개장 전 주가 올라"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 3,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 4,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 5,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
    Notification(
        icon = Icons.Rounded.Star,
        category = "추천 뉴스",
        timePassed = 1,
        title = "긴급대출, 만기연장... 금융업계, 수해 지원 방안 내놔"
    ),
)