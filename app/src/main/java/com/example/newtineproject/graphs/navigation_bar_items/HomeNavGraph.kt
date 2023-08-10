package com.example.newtineproject.graphs.navigation_bar_items

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.home.HomeScreen
import com.example.newtineproject.ui.screens.home.article.ArticleScreen
import com.example.newtineproject.ui.screens.home.habitsetting.HabitSettingScreen
import com.example.newtineproject.ui.screens.home.notification.NotificationScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        route = NavigationBarScreen.Home.route,
        startDestination = HomeDetailScreen.Home.route
    ) {
        composable(route = HomeDetailScreen.Home.route) {
            HomeScreen(
                navController = navController,
                paddingValues = paddingValues
            )
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