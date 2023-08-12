package com.example.newtineproject.graphs.navigation_bar_items

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.scrap.ScrapScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScrapNavGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        NavHost(
            navController = navController,
            route = NavigationBarScreen.Scrap.route,
            startDestination = ScrapDetailScreen.Scrap.route
        ) {
            composable(route = ScrapDetailScreen.Scrap.route) {
                ScrapScreen(navController = navController)
            }
        }
    }
}

sealed class ScrapDetailScreen(val route: String) {
    data object Scrap: ScrapDetailScreen(route = "SCRAP")
}