package com.example.newtineproject.ui.screens.newtech

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.R
import com.example.newtineproject.graphs.navigation_bar_items.NewTechDetailScreen
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.NewTineProjectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTechScreen(navController: NavController) {
    var cnt_article=0
    var reading_time=0
    var goal_state= mutableMapOf<Int,String>(
        0 to "아직 달성한 목표가 없어요!",
        1 to "3개 중에 1개 완료했어요!",
        2 to "3개 중에 2개 완료했어요!",
        3 to "3개 중에 3개 완료했어요!",
        )


    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("뉴테크") },
                modifier = Modifier.background(
                    Color.White),
                navigationIcon = {
                    IconButton(
                        onClick = { /* Handle back navigation */ }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* Handle more button click */ }
                    ) {
                        Icon(Icons.Filled.MoreVert, contentDescription = null)
                    }
                }
            )
        },
        content = {padding->
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(top = 50.dp)
                    .fillMaxSize()

            ) {
                Text(text = "오늘의 목표량 달성"
                    , fontSize = 20.sp
                    , modifier = Modifier.padding(top=20.dp, start = 20.dp, bottom = 10.dp)
                    )

                Text(text = "${goal_state[0]}",
                    modifier = Modifier
                        .padding(start=20.dp)
                    , color = Color.Gray
                )
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 30.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.coin2)
                        , contentDescription = "coin"
                        , modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()

                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Box(
                        modifier = Modifier
                            .background(Color(android.graphics.Color.parseColor("#F3F4F6")))
                            .wrapContentWidth()
                            .padding(15.dp)

                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "오늘 읽은 기사 갯수")
                            Text(text = "${cnt_article}개",
                                fontSize = 30.sp

                                )
                        }

                    }

                    Box(
                        modifier = Modifier
                            .background(Color(android.graphics.Color.parseColor("#F3F4F6")))
                            .wrapContentWidth()
                            .padding(15.dp)

                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "기사에 머물렀던 시간")
                            Text(text = "${reading_time}분",
                                fontSize = 30.sp

                            )

                        }

                    }

                }

                Button(
                    onClick = {
                              navController.navigate(route = NewTechDetailScreen.Status.route)
                    },
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                        .padding(10.dp)
                        .border(0.5.dp, LightBlue, RectangleShape)
                    ,
                    colors = ButtonDefaults.buttonColors(Color.White),


                ) {
                    Text(text = "습관 달성 현황",
                        fontSize = 15.sp,
                        color = LightBlue,
                        modifier = Modifier.padding(10.dp)
                    )
                    
                }

                




                
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewTineProjectTheme {
        NewTechScreen(navController = rememberNavController())
    }
}