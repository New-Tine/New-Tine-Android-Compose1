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
        startDestination = MyPageDetailScreen.MyPage.route
    ) {
        composable(route = MyPageDetailScreen.MyPage.route) {
            MyPageScreen(navController = navController)
        }
        composable(route = MyPageDetailScreen.Second.route) {
            TmpNextScreen(navController = navController)
        }
    }
}

sealed class MyPageDetailScreen(val route: String) {
    data object MyPage: MyPageDetailScreen(route = "MYPAGE")
    data object Second: MyPageDetailScreen(route = "SECOND")
}