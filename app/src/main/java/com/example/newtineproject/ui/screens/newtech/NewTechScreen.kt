package com.example.newtineproject.ui.screens.newtech

import android.annotation.SuppressLint
import android.hardware.lights.Light
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newtineproject.R
import com.example.newtineproject.graphs.navigation_bar_items.NewTechDetailScreen
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.NewTineProjectTheme


@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTechScreen(navController: NavController) {
    var cntArticle=0
    var readingTime=0
    var goalState= mutableMapOf<Int,String>(
        0 to "아직 달성한 목표가 없어요!",
        1 to "3개 중에 1개 완료했어요!",
        2 to "3개 중에 2개 완료했어요!",
        3 to "3개 중에 3개 완료했어요!",
        )

    val buttonGradient = Brush.horizontalGradient(listOf(Color(0xFF0294FF),Color(0x009BD5FF)))


    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("뉴테크") },
                modifier = Modifier.background(
                    Color.White),
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
                modifier=Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween

            ){
                Column(
                    modifier = Modifier
                        .padding(top = padding.calculateTopPadding())
                        .padding(10.dp)
                        .background(Color.White)
                        .fillMaxWidth()

                ) {
                    Text(
                        text = "오늘의 목표량 달성",
                        modifier = Modifier.padding(top=10.dp, start = 10.dp, bottom = 10.dp),
                        style = LocalTextStyle.current.copy(
                            fontWeight = FontWeight(500),
                            fontSize = 24.sp
                        )
                    )

                    Text(
                        text = "${goalState[0]}",
                        modifier = Modifier
                            .padding(start=10.dp),
                        style = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF6D7280)
                        )
                    )

                    //place mission coin flag here
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

                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){

                        Box{
                            Card(
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .padding(20.dp)
                                    .graphicsLayer {
                                        shape = RoundedCornerShape(4.dp)
                                        shadowElevation = 10f
                                    }
                            ){
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .width(160.dp)
                                        .background(Color.White)
                                        .padding(
                                            top = 15.dp,
                                            bottom = 15.dp,
                                            start = 15.dp,
                                            end = 15.dp
                                        )
                                ) {
                                    Text(
                                        text = "오늘 읽은 기사 갯수",
                                        style = LocalTextStyle.current.copy(
                                            fontSize = 14.sp
                                        )
                                    )
                                    Text(text = "${cntArticle}개",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight(500)

                                    )
                                }
                            }
                        }

                        Box{
                            Card(
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .padding(20.dp)
                                    .graphicsLayer {
                                        shape = RoundedCornerShape(4.dp)
                                        shadowElevation = 10f
                                    }
                            ){
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .width(160.dp)
                                        .background(Color.White)
                                        .padding(
                                            top = 15.dp,
                                            bottom = 15.dp,
                                            start = 15.dp,
                                            end = 15.dp
                                        )
                                ) {
                                    Text(
                                        text = "기사에 머물렀던 시간",
                                        style = LocalTextStyle.current.copy(
                                            fontSize = 14.sp
                                        )
                                    )
                                    Text(text = "${readingTime}분",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                }
                            }
                        }


                    }

                    Box(){
                        GradientButton(
                            textString = "습관 달성 현황 보기",
                            gradient = buttonGradient,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            onClick = {
                                navController.navigate(route = NewTechDetailScreen.Status.route)
                            }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.flag_newtech),
                            contentDescription = "flag",
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .size(87.dp),
                            alignment = Alignment.TopEnd
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
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

@Composable
fun GradientButton(
    textString: String,
    gradient: Brush,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = {onClick()},
        shape = RoundedCornerShape(4.dp)
    ){
        Box(
            modifier = Modifier
                .background(gradient)
                .then(modifier),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text=textString,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(10.dp),
                fontWeight = FontWeight(500)
            )
        }
    }
}