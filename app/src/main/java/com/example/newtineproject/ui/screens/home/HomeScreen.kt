package com.example.newtineproject.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtineproject.R
import com.example.newtineproject.ui.screens.home.components.HomeHorizontalPager
import com.example.newtineproject.ui.theme.LightBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
        topBar = { FakeTopAppBar() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ButtonsBelowTopBar(itemTextList = listOf("습관 설정", "실시간 토론"))
            
            Spacer(modifier = Modifier.height(15.dp))

            TodayNewTechWithLogo(innerPadding = it)

            ReadCountBox() // 파라미터에 몇 개 읽었는지 읽을 수 있도록 나중에 설정

            Spacer(modifier = Modifier.height(10.dp))

            HomeHorizontalPager()
        }
    }
}


@Composable
fun ButtonsBelowTopBar(itemTextList: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        itemTextList.forEach { text ->
            ButtonOf(buttonText = text)
        }
    }
}

@Composable
fun ButtonOf(buttonText: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .width(170.dp)
            .height(55.dp),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(start = 50.dp)
    ) {
        Text(
            text = buttonText,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF374151)
            )
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeTopAppBar() {
    TopAppBar(
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
        title = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.newtine_topbar_logo),
                    contentDescription = "newtine_topbar_logo"
                )
            }
        },
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
}

@Composable
fun TodayNewTechWithLogo(innerPadding: PaddingValues) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 17.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "오늘의 뉴테크",
            style = MaterialTheme.typography.titleLarge
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
}

@Composable
fun ReadCountBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp, vertical = 10.dp)
            .height(55.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(LightBlue),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 17.dp),
            text = "현재 2개 읽으셨어요",
            style = LocalTextStyle.current.copy(
                fontSize = 18.sp,
                color = Color.White
            )
        )
    }
}