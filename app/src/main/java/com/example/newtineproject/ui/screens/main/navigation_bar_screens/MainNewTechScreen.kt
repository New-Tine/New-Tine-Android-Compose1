package com.example.newtineproject.ui.screens.main.navigation_bar_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.navigation_bar_items.HomeNavGraph
import com.example.newtineproject.graphs.navigation_bar_items.NewTechNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNewTechScreen(
    navController: NavHostController = rememberNavController(),
    bottomPadding: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(bottom = bottomPadding.calculateBottomPadding())
    ) {
        NewTechNavGraph(navController = navController)
    }
}