package com.example.newtineproject.ui.screens.main.navigation_bar_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.navigation_bar_items.HomeNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainHomeScreen(
    navController: NavHostController = rememberNavController(),
    bottomPadding: PaddingValues
) {
    HomeNavGraph(
        navController = navController,
        bottomPadding = bottomPadding
    )
}