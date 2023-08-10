package com.example.newtineproject.ui.screens.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.newtineproject.graphs.navigation_bar_items.MyPageDetailScreen

@Composable
fun MyPageScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = {
            navController.navigate(MyPageDetailScreen.Second.route)
        }) {
            Text(text = "Go to next screen")
        }
    }
}