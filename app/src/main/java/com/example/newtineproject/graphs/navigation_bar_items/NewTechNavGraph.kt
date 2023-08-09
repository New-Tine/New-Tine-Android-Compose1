package com.example.newtineproject.graphs.navigation_bar_items

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.newtech.NewTechScreen
import com.example.newtineproject.ui.screens.newtech.achievementstatus.AchievementStatus

fun NavGraphBuilder.newTechNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationBarScreen.NewTech.route,
        startDestination = NewTechDetailScreen.NewTech.route
    ) {
        composable(route = NewTechDetailScreen.NewTech.route) {
            NewTechScreen(navController = navController)
        }
        composable(route = NewTechDetailScreen.Status.route) {
            AchievementStatus(navController = navController)
        }
    }
}

sealed class NewTechDetailScreen(val route: String) {
    data object NewTech: NewTechDetailScreen(route = "NEWTECH")
    data object Status: NewTechDetailScreen(route = "STATUS")
}