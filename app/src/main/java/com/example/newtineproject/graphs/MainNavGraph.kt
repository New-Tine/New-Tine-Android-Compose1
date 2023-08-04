package com.example.newtineproject.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost (
        navController = navController,
        route = Graph.MAIN,
        startDestination = MainScreen.Home.route
    ) {
        // Bottom bar navigation implementation
        homeNavGraph(navController)
//        composable(route = MainScreen.Home.route) {
//            HomeNavGraph(navController = navController)
//        }
//        composable(route = MainScreen.NewTech.route) {
//            NewTechScreen()
//        }
//        composable(route = MainScreen.Scrap.route) {
//            ScrapScreen()
//        }
//        composable(route = MainScreen.MyPage.route) {
//            MyPageScreen()
//        }

    }
}

sealed class MainScreen(val route: String) {
    data object Home: MainScreen(route = "HOME")
    data object NewTech: MainScreen(route = "NEW_TECH")
    data object Scrap: MainScreen(route = "SCRAP")
    data object MyPage: MainScreen(route = "MY_PAGE")
}

