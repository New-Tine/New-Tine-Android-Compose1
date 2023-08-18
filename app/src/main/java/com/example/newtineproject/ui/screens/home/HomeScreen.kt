package com.example.newtineproject.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newtineproject.R
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import com.example.newtineproject.ui.screens.home.components.HomeHorizontalPager
import com.example.newtineproject.ui.theme.HomeButtonBelowTopBarColor
import com.example.newtineproject.ui.theme.LightBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavHostController
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
            ButtonsBelowTopBar(navController = navController)

            Spacer(modifier = Modifier.height(15.dp))

            TodayNewTechAndReadCountBoxWithCoins()

            Spacer(modifier = Modifier.height(10.dp))

            HomeHorizontalPager()
        }
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
fun ButtonsBelowTopBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 17.dp, end = 17.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ButtonOf(
            buttonText = "습관 설정",
            buttonIcon = painterResource(id = R.drawable.logo_calendar_1),
            navController = navController,
            destination = HomeDetailScreen.HabitSetting.route
        )
        ButtonOf(
            buttonText = "실시간 토론",
            buttonIcon = painterResource(id = R.drawable.logo_debate_1),
            navController = null,
            destination = ""
        )
    }
}

@Composable
fun ButtonOf(
    buttonText: String,
    buttonIcon: Painter,
    navController: NavHostController?,
    destination: String
) {
    Box {
        Button(
            onClick = { navController?.navigate(destination) },
            modifier = Modifier
                .width(170.dp)
                .height(65.dp)
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(HomeButtonBelowTopBarColor),
            shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(start = 55.dp)
        ) {
            Text(
                text = buttonText,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF374151)
                )
            )
        }
        Image(
            modifier = Modifier.size(65.dp),
            painter = buttonIcon,
            contentDescription = "Logo Calendar"
        )
    }
}

@Composable
fun TodayNewTechAndReadCountBoxWithCoins() {
    Box {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TodayNewTechWithLogo()
            Spacer(modifier = Modifier.height(2.dp))
            ReadCountBox()
        }
        Box(modifier = Modifier.offset(x = 260.dp, y = 30.dp)) {
            Image(
                modifier = Modifier.size(47.dp),
                painter = painterResource(id = R.drawable.coin_home_1),
                contentDescription = "coin_1"
            )
            Image(
                modifier = Modifier
                    .size(83.dp)
                    .offset(x = 1.dp, y = 4.dp),
                painter = painterResource(id = R.drawable.coin_home_2),
                contentDescription = "coin_2"
            )
        }
    }
}

@Composable
fun TodayNewTechWithLogo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 17.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "오늘의 뉴테크",
            style = LocalTextStyle.current.copy(
                fontSize = 21.sp,
                fontWeight = FontWeight(600)
            )
        )
        Image(
            painter = painterResource(id = R.drawable.newtine_logo),
            contentDescription = "newtine logo",
            modifier = Modifier
                .size(50.dp)
                .offset(y = 3.dp)
        )
    }
}

@Composable
fun ReadCountBox() {
    // 파라미터에 몇 개 읽었는지 읽을 수 있도록 나중에 설정
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp, vertical = 10.dp)
            .height(55.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(LightBlue, Color(0xFF13B5FF))
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 17.dp),
            text = "현재 ",
            style = LocalTextStyle.current.copy(
                fontSize = 18.sp,
                color = Color.White
            )
        )
        Text(
            text = "2",
            style = LocalTextStyle.current.copy(
                fontSize = 25.sp,
                color = Color.White
            )
        )
        Text(
            text = "개 읽으셨어요",
            style = LocalTextStyle.current.copy(
                fontSize = 18.sp,
                color = Color.White
            )
        )
    }
}