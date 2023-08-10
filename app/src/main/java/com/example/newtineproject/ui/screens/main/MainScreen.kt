package com.example.newtineproject.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.graphs.MainNavGraph
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.ui.screens.home.components.HomeTopAppBar
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.NavigationBarColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val navigationBarItems = listOf(
        NavigationBarScreen.Home,
        NavigationBarScreen.NewTech,
        NavigationBarScreen.Scrap,
        NavigationBarScreen.MyPage
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarVisible = navigationBarItems.any {it.route == currentDestination?.route}
    val fabAndTopVisible = NavigationBarScreen.Home.route == currentDestination?.route

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = NavigationBarColor,
                modifier = Modifier
                    .shadow(elevation = 15.dp)
            ) {
                navigationBarItems.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    ) {
        MainNavGraph(
            navController = navController,
            bottomPadding = it
        )
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = { Text(text = screen.title) },
        icon = { Icon(painter = painterResource(screen.iconResourceId), contentDescription = "Navigation Icon") },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        },
        colors = NavigationBarItemDefaults.colors(

            selectedIconColor = LightBlue,
            selectedTextColor = LightBlue,
            unselectedIconColor = Color.LightGray,
            unselectedTextColor = Color.LightGray,
            indicatorColor = NavigationBarColor
        )
    )
}