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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    drawerState: DrawerState,
    scope: CoroutineScope,
    onNotificationsClick: () -> Unit,
    ) {
    CenterAlignedTopAppBar(
        title = { Text(text = "") },
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
                    onNotificationsClick()
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