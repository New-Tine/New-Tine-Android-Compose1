package com.example.newtineproject.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.ui.screens.notification.NotificationScreen

fun NavGraphBuilder.homeDetailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = HomeDetailScreen.Notification.route
    ) {
        composable(route = HomeDetailScreen.Notification.route) {
            NotificationScreen() {
                navController.popBackStack()
            }
        }
    }
}

sealed class HomeDetailScreen(val route: String) {
    data object Notification: HomeDetailScreen(route = "NOTIFICATION")
}