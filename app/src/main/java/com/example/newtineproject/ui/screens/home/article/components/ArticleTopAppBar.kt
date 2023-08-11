package com.example.newtineproject.ui.screens.home.article.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleTopAppBar(navController: NavController) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back",
                    tint = Color.Gray
                )
            }
        },
        title = { Text(text = "기사") },
        actions = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Go to search screen"
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(HomeDetailScreen.Notification.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Go to notification screen",
                )
            }
        }
    )
}