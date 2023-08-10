package com.example.newtineproject.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.R
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import com.example.newtineproject.ui.screens.home.article.ArticleScreen
import com.example.newtineproject.ui.screens.home.habitsetting.HabitSettingScreen
import com.example.newtineproject.ui.screens.home.notification.NotificationScreen
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MainHomeScreen
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MainMyPageScreen
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MainNewTechScreen
import com.example.newtineproject.ui.screens.main.navigation_bar_screens.MainScrapScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost (
        navController = navController,
        route = Graph.MAIN,
        startDestination = NavigationBarScreen.Home.route
    ) {
        // Bottom bar navigation implementation
        navigation(
            route = NavigationBarScreen.Home.route,
            startDestination = MainDetailScreen.Home.route
        ) {
            composable(route = MainDetailScreen.Home.route) {
                MainHomeScreen(paddingValues = paddingValues)
            }
            composable(route = "${MainDetailScreen.Article.route}/{indexFromDrawer}") { backStackEntry ->
                ArticleScreen(
                    navController = navController,
                    indexFromDrawer = backStackEntry.arguments?.getString("indexFromDrawer") ?: "0"
                )
            }
            composable(route = MainDetailScreen.Notification.route) {
                NotificationScreen(navController = navController)
            }
            composable(route = MainDetailScreen.HabitSetting.route) {
                HabitSettingScreen(navController = navController)
            }

        }
        composable(route = NavigationBarScreen.NewTech.route) {
            MainNewTechScreen(paddingValues = paddingValues)
        }
        composable(route = NavigationBarScreen.Scrap.route) {
            MainScrapScreen(paddingValues = paddingValues)
        }
        composable(route = NavigationBarScreen.MyPage.route) {
            MainMyPageScreen(paddingValues = paddingValues)
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

sealed class MainDetailScreen(val route: String) {
    data object Home: MainDetailScreen(route = "HOME")
    data object Notification: MainDetailScreen(route = "NOTIFICATION")
    data object Article: MainDetailScreen(route = "ARTICLE")
    data object HabitSetting: MainDetailScreen(route = "HABIT_SETTING")
}