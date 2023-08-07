package com.example.newtineproject.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.MainNavGraph
import com.example.newtineproject.ui.screens.home.Habbit_making_Screen
import com.example.newtineproject.ui.screens.mytech.AchievementScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold {
        MainNavGraph(navController = navController)

    }
}