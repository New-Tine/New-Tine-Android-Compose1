package com.example.newtineproject.graphs

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.R
import com.example.newtineproject.graphs.navigation_bar_items.MyPageNavGraph
import com.example.newtineproject.graphs.navigation_bar_items.NewTechNavGraph
import com.example.newtineproject.graphs.navigation_bar_items.ScrapNavGraph
import com.example.newtineproject.graphs.navigation_bar_items.homeNavGraph

@Composable
fun MainNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost (
        navController = navController,
        route = Graph.MAIN,
        startDestination = NavigationBarScreen.Home.route,
        // 화면 전환 시 크로스 페이드 효과 지우고 싶을 때 주석 처리 빼면 됨.
//        enterTransition = { EnterTransition.None },
//        exitTransition = { ExitTransition.None }
    ) {
        // Bottom bar navigation implementation
        homeNavGraph(
            navController = navController,
            paddingValues = paddingValues
        )
        composable(route = NavigationBarScreen.NewTech.route) {
            NewTechNavGraph(paddingValues = paddingValues)
        }
        composable(route = NavigationBarScreen.Scrap.route) {
            ScrapNavGraph(paddingValues = paddingValues)
        }
        composable(route = NavigationBarScreen.MyPage.route) {
            MyPageNavGraph(paddingValues = paddingValues)
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

