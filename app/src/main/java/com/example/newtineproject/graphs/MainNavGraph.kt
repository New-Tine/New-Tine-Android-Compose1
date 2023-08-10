package com.example.newtineproject.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.R
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MainHomeScreen
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MainMyPageScreen
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MainNewTechScreen
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MainScrapScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    bottomPadding: PaddingValues
) {
    NavHost (
        navController = navController,
        route = Graph.MAIN,
        startDestination = NavigationBarScreen.Home.route
    ) {
        // Bottom bar navigation implementation
        composable(route = NavigationBarScreen.Home.route) {
            MainHomeScreen(bottomPadding = bottomPadding)
        }
        composable(route = NavigationBarScreen.NewTech.route) {
            MainNewTechScreen(bottomPadding = bottomPadding)
        }
        composable(route = NavigationBarScreen.Scrap.route) {
            MainScrapScreen(bottomPadding = bottomPadding)
        }
        composable(route = NavigationBarScreen.MyPage.route) {
            MainMyPageScreen(bottomPadding = bottomPadding)
        }
    }
}

sealed class NavigationBarScreen(
    val route: String,
    val title: String,
    val iconResourceId: Int
) {
    data object Home: NavigationBarScreen(
        route = "home",
        title = "홈",
        iconResourceId = R.drawable.home
    )
    data object NewTech: NavigationBarScreen(
        route = "newtech",
        title = "뉴테크",
        iconResourceId = R.drawable.newtech
    )
    data object Scrap: NavigationBarScreen(
        route = "scrap",
        title = "스크랩",
        iconResourceId = R.drawable.scrap
    )
    data object MyPage: NavigationBarScreen(
        route = "mypage",
        title = "마이페이지",
        iconResourceId = R.drawable.mypage
    )
}