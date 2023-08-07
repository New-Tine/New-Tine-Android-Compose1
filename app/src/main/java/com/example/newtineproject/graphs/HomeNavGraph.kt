package com.example.newtineproject.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.ui.screens.home.article.ArticleScreen
import com.example.newtineproject.ui.screens.home.HomeScreen
import com.example.newtineproject.ui.screens.home.habbitsetting.HabitSettingScreen
import com.example.newtineproject.ui.screens.home.notification.NotificationScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = "home",
        startDestination = HomeDetailScreen.Home.route
    ) {
        composable(route = HomeDetailScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = HomeDetailScreen.Notification.route) {
            NotificationScreen(navController = navController)
        }
        composable(route = "${HomeDetailScreen.Article.route}/{indexFromDrawer}") { backStackEntry ->
            ArticleScreen(
                navController = navController,
                indexFromDrawer = backStackEntry.arguments?.getString("indexFromDrawer") ?: "0"
            )
        }
        composable(route = HomeDetailScreen.HabitSetting.route) {
            HabitSettingScreen(navController = navController)
        }
    }
}

sealed class HomeDetailScreen(val route: String) {
    data object Home: HomeDetailScreen(route = "HOME")
    data object Notification: HomeDetailScreen(route = "NOTIFICATION")
    data object Article: HomeDetailScreen(route = "ARTICLE")
    data object HabitSetting: HomeDetailScreen(route = "HABIT_SETTING")
}