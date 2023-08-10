package com.example.newtineproject.ui.screens.main.navigation_bar_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.navigation_bar_items.MyPageNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMyPageScreen(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        MyPageNavGraph(navController = navController)
    }
}