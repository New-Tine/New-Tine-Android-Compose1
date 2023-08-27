package com.example.newtineproject.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newtineproject.ui.screens.login.screens.LLoginScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.LOGIN,
    ) {
        composable(route=Graph.LOGIN){
            LLoginScreen()
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val LOGIN= "login_graph"
    const val SIGNUP="signup_graph"
    const val ARTICLE="Article_graph"
}