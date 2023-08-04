package com.example.newtineproject.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.ui.screens.home.HomeScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost (
        navController = navController,
        route = Graph.MAIN,
        startDestination = MainScreen.Home.route
    ) {
        // Bottom bar navigation implementation
        composable(route = MainScreen.Home.route) {
            HomeScreen {
                navController.navigate(Graph.DETAILS)
            }
        }
//        composable(route = MainScreen.Home.route) {
//            NewTechScreen()
//        }
//        composable(route = MainScreen.Home.route) {
//            ScrapScreen()
//        }
//        composable(route = MainScreen.Home.route) {
//            MyPageScreen()
//        }
        homeDetailNavGraph(navController = navController)
    }
}

sealed class MainScreen(val route: String) {
    data object Home: MainScreen(route = "HOME")
    data object NewTech: MainScreen(route = "NEW_TECH")
    data object Scrap: MainScreen(route = "SCRAP")
    data object MyPage: MainScreen(route = "MY_PAGE")
}

