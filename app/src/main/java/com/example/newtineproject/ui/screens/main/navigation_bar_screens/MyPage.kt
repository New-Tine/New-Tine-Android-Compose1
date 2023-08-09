package com.example.newtineproject.ui.screens.main.navigation_bar_screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.navigation_bar_items.MyPageNavGraph
import com.example.newtineproject.ui.screens.mypage.MyPageScreen

@Composable
fun MyPage(navController: NavHostController = rememberNavController()) {
    MyPageNavGraph(navController = navController)
}