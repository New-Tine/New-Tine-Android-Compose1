package com.example.newtineproject.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import com.example.newtineproject.ui.theme.LightGray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeModalDrawerSheet(
    items: List<String>,
    selectedItem: MutableState<String>,
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController
) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(0.35f),
    ) {
        Spacer(Modifier.fillMaxHeight(0.15f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "카테고리",
                style = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500)
                )
            )
        }
        Spacer(Modifier.fillMaxHeight(0.05f))
        items.forEach { item ->
            NavigationDrawerItem(
                label = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item,
                            style = LocalTextStyle.current.copy(
                                fontSize = 14.sp,
                                color = LightGray
                            )
                        )
                    }
                },
                selected = false, // item == selectedItem.value, 선택한 것 티 낼지 안 낼지 물어보기!
                onClick = {
                    scope.launch { drawerState.close() }
                    // selectedItem.value = item
                    navController.navigate(
                        route = "${HomeDetailScreen.Article.route}/${items.indexOf(item)}"
                    )
                },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
        // ...other drawer items
    }
}