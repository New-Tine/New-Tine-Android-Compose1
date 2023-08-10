package com.example.newtineproject.graphs.navigation_bar_items

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.scrap.ScrapScreen

@Composable
fun ScrapNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationBarScreen.Scrap.route,
        startDestination = ScrapDetailScreen.Scrap.route
    ) {
        composable(route = ScrapDetailScreen.Scrap.route) {
            ScrapScreen(navController = navController)
        }
    }
}

sealed class ScrapDetailScreen(val route: String) {
    data object Scrap: ScrapDetailScreen(route = "SCRAP")
}