package com.example.newtineproject.graphs.navigation_bar_items

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.newtech.NewTechScreen
import com.example.newtineproject.ui.screens.newtech.achievementstatus.AchievementStatus

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewTechNavGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        NavHost(
            navController = navController,
            route = NavigationBarScreen.NewTech.route,
            startDestination = NewTechDetailScreen.NewTech.route
        ) {
            composable(route = NewTechDetailScreen.NewTech.route) {
                NewTechScreen(navController = navController)
            }
            composable(route = NewTechDetailScreen.Status.route) {
                AchievementStatus(navController = navController)
            }
        }
    }
}

sealed class NewTechDetailScreen(val route: String) {
    data object NewTech: NewTechDetailScreen(route = "NEWTECH")
    data object Status: NewTechDetailScreen(route = "STATUS")
}