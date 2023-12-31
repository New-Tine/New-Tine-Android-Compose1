package com.example.newtineproject.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.home.Category
import com.example.newtineproject.graphs.MainNavGraph
import com.example.newtineproject.graphs.NavigationBarScreen
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import com.example.newtineproject.ui.screens.home.components.HomeModalDrawerSheet
import com.example.newtineproject.ui.screens.home.components.HomeTopAppBar
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.NavigationBarColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val items = Category.values().map { it.categoryName }
    val selectedItem = remember { mutableStateOf(items[0]) }


    val navigationBarItems = listOf(
        NavigationBarScreen.Home,
        NavigationBarScreen.NewTech,
        NavigationBarScreen.Scrap,
        NavigationBarScreen.MyPage
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topBarVisible = currentDestination?.route == HomeDetailScreen.Home.route
    val bottomBarVisible = currentDestination?.route != HomeDetailScreen.Notification.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            HomeModalDrawerSheet(
                items = items,
                selectedItem = selectedItem,
                drawerState = drawerState,
                scope = scope,
                navController = navController
            )
        },
        gesturesEnabled = topBarVisible
    ) {
        Scaffold(
            topBar = {
                AnimatedVisibility(
                    visible = topBarVisible,
                    enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                    exit = ExitTransition.None
                ) {
                    HomeTopAppBar(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    )
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = bottomBarVisible,
                    enter = slideInVertically(animationSpec = tween(durationMillis = 100)) { it / 2 },
                    exit = slideOutVertically(animationSpec = tween(durationMillis = 100)) { it / 2 }
                ) {
                    NavigationBar(
                        modifier = Modifier.shadow(elevation = 15.dp),
                        containerColor = NavigationBarColor,
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
            }
        ) {
            MainNavGraph(
                navController = navController,
                paddingValues = it
            )
        }
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