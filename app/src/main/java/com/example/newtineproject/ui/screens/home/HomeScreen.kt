package com.example.newtineproject.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newtineproject.R
import com.example.newtineproject.ui.screens.home.components.HomeHorizontalPager

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { }
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
                        onClick = { },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Go to search screen"
                        )
                    }
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Go to notification screen"
                        )
                    }
                }
            )
        },
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