package com.example.newtineproject.graphs.navigation_bar_items

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.scrap.ScrapScreen

@Composable
fun ScrapNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationBarScreen.Scrap.route,
        startDestination = ScrapDetailedScreen.Scrap.route
    ) {
        composable(route = ScrapDetailedScreen.Scrap.route) {
            ScrapScreen(navController = navController)
        }
    }
}

sealed class ScrapDetailedScreen(val route: String) {
    data object Scrap: ScrapDetailedScreen(route = "SCRAP")
}