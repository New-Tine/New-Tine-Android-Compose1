package com.example.newtineproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import com.example.newtineproject.ui.screens.categoryNews.PagerScreen
import com.example.newtineproject.ui.theme.NewTineProjectTheme
import com.example.newtineproject.ui.screens.notification.NotificationScreen
import com.google.accompanist.pager.ExperimentalPagerApi

import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.RootNavGraph
import com.example.newtineproject.ui.theme.NewTineProjectTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewTineProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NotificationScreen()
                    PagerScreen()

                    RootNavGraph(navController = rememberNavController())

                }
            }
        }
    }
}