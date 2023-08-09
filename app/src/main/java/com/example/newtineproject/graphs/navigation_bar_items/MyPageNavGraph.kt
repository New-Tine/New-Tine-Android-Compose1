package com.example.newtineproject.graphs.navigation_bar_items

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MyPage
import com.example.newtineproject.ui.screens.mypage.MyPageScreen

@Composable
fun MyPageNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationBarScreen.MyPage.route,
        startDestination = MyPageDetailedScreen.MyPage.route
    ) {
        composable(route = NavigationBarScreen.MyPage.route) {
            MyPageScreen(navController = navController)
        }
    }
}

sealed class MyPageDetailedScreen(val route: String) {
    data object MyPage: MyPageDetailedScreen(route = "MYPAGE")
}