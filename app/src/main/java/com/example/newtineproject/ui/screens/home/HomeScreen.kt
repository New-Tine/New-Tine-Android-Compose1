package com.example.newtineproject.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.home.Category
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import com.example.newtineproject.ui.screens.home.components.HomeHorizontalPager
import com.example.newtineproject.ui.screens.home.components.HomeModalDrawerSheet
import com.example.newtineproject.ui.screens.home.components.HomeTopAppBar
import com.example.newtineproject.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val items = Category.values().map { it.categoryName }
    val selectedItem = remember { mutableStateOf(items[0]) }

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
        }
    ) {
        // Screen content
        Scaffold(
            topBar = {
                HomeTopAppBar(
                    navController = navController,
                    drawerState = drawerState,
                    scope = scope
                )
            },
            floatingActionButton = {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth(0.915f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(HomeDetailScreen.HabitSetting.route)
                        },
                        shape = CircleShape,
                        containerColor = LightBlue,
                        contentColor = Color.White,
                        modifier = Modifier.width(256.dp)
                    ) {
                        Text(
                            text = "나만의 습관 설정",
                        )
                    }
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        containerColor = LightBlue,
                        contentColor = Color.White,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.home_chat),
                            contentDescription = null,
                        )
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = it.calculateTopPadding(), start = 17.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "오늘의 뉴테크",
                        style = MaterialTheme.typography.headlineMedium
                        // figma랑 똑같이 구현할 시 (폰트도 추가 고려)
//                        style = LocalTextStyle.current.copy(
//                            fontSize = 24.sp,
//                            fontWeight = FontWeight(600)
//                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.newtine_logo),
                        contentDescription = "newtine logo",
                        modifier = Modifier
                            .width(44.dp)
                            .padding(top = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                HomeHorizontalPager()
            }
        }
    }
}