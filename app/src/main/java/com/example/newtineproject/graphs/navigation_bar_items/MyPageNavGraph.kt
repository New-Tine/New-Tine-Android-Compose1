package com.example.newtineproject.graphs.navigation_bar_items

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.mypage.MyPageScreen
import com.example.newtineproject.ui.screens.mypage.TmpNextScreen

@Composable
fun MyPageNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationBarScreen.MyPage.route,
        startDestination = MyPageDetailedScreen.MyPage.route
    ) {
        composable(route = MyPageDetailedScreen.MyPage.route) {
            MyPageScreen(navController = navController)
        }
        composable(route = MyPageDetailedScreen.Second.route) {
            TmpNextScreen(navController = navController)
        }
    }
}

sealed class MyPageDetailedScreen(val route: String) {
    data object MyPage: MyPageDetailedScreen(route = "MYPAGE")
    data object Second: MyPageDetailedScreen(route = "SECOND")
}