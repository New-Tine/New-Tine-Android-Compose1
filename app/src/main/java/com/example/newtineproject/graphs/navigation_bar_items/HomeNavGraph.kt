package com.example.newtineproject.graphs.navigation_bar_items

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.home.HomeScreen
import com.example.newtineproject.ui.screens.home.article.ArticleScreen
import com.example.newtineproject.ui.screens.home.habitsetting.HabitSettingScreen
import com.example.newtineproject.ui.screens.home.notification.NotificationScreen
import com.example.newtineproject.ui.screens.home.search.SearchScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    navigation(
        route = NavigationBarScreen.Home.route,
        startDestination = HomeDetailScreen.Home.route
    ) {
        composable(route = HomeDetailScreen.Home.route) {
            HomeScreen(
                paddingValues = paddingValues,
                navController = navController
            )
        }
        composable(route = "${HomeDetailScreen.Article.route}/{indexFromDrawer}") { backStackEntry ->
            ArticleScreen(
                navController = navController,
                indexFromDrawer = backStackEntry.arguments?.getString("indexFromDrawer") ?: "0",
                paddingValues = paddingValues
            )
        }
        composable(route = HomeDetailScreen.Notification.route) {
            NotificationScreen(navController = navController)
        }
        composable(route = HomeDetailScreen.HabitSetting.route) {
            HabitSettingScreen(navController = navController)
        }
        composable(route = HomeDetailScreen.Search.route){
            SearchScreen(navController = navController)
        }
    }
}

sealed class HomeDetailScreen(val route: String) {
    data object Home: HomeDetailScreen(route = "HOME")
    data object Notification: HomeDetailScreen(route = "NOTIFICATION")
    data object Article: HomeDetailScreen(route = "ARTICLE")
    data object HabitSetting: HomeDetailScreen(route = "HABIT_SETTING")
    data object Search: HomeDetailScreen(route = "SEARCH")
}