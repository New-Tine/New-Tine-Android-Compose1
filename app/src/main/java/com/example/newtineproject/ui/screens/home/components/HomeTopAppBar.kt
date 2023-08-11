package com.example.newtineproject.ui.screens.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController,
    ) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch { drawerState.open() }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Go back"
                )
            }
        },
        title = { Text(text = "") },
        actions = {
            IconButton(
                onClick = {

                },
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Go to search screen"
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(HomeDetailScreen.Notification.route)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Go to notification screen"
                )
            }
        }
    )
}