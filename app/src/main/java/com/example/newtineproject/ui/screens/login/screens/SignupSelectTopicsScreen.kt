package com.example.newtineproject.ui.screens.login.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newtineproject.graphs.SignupScreen
import com.example.newtineproject.ui.screens.login.components.SignupTopAppBar
import com.example.newtineproject.ui.theme.Grey
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.MiddleGrey


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupSelectTopicsScreen(navController: NavController){
    val topicList= mutableListOf<String>("IT","정치 외교","국제 사회",
        "운동","과학 기술","사회 문화","연예 활동","경제 금융","증권","취업","공부","자동차",
        "블로그","최신 드라마","K-POP","반려 동물","쇼핑","국내 여행","해외 여행","봉사 활동"
        ,"애니메이션","영화"
    )

    Scaffold (
        topBar = {
            SignupTopAppBar(navController = navController)
        },
            content={padding->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 70.dp, start = 20.dp, end = 20.dp)
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "관심 주제를",
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold


                    )
                    Text(
                        text = "선택해주세요",
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold


                    )
                    Text(
                        text = "관심 주제를 2개이상 선택해주세요",
                        fontSize = 15.sp,
                        color= Grey,
                        modifier = Modifier.padding(top=7.dp)

                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    val itemsPerline=3
                    //val lines=(topicList.size+itemsPerline-1)/itemsPerline
                    val lines=topicList.size/itemsPerline+1

                    LazyColumn(

                        content = {
                        items(lines){lineIndex->
                            val startIndex=lineIndex*itemsPerline
                            val endIndex=startIndex+itemsPerline
                            //itemsInLine에서 subList할때 마지막 index앞까지 자르니까 또 +1하기 싫어서 여기서 애초에 -1을 안해줌

                            val itemsInLine = topicList.subList(startIndex, endIndex.coerceAtMost(topicList.size))

                            Log.d("index",itemsInLine.toString())

                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                content = {
                                items(itemsInLine.size){
                                        index ->  TopicList(topic = itemsInLine[index])
                                }
                            },
                                modifier= Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 0.dp)

                            )

                        }

                    },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                        , modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "건너뛰기",
                            color = Grey
                        )

                    }

                    Button(
                        onClick = {
                            navController.navigate(SignupScreen.Agreement.route)

                    },
                        colors = ButtonDefaults.buttonColors(LightBlue),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(50.dp),

                        shape = RoundedCornerShape(2.dp)


                    ) {
                        Text(text = "다음",
                            color = Color.White,
                            fontSize = 15.sp
                        )


                    }




                }


            }

        )
}

@Composable
fun TopicList(topic:String){
    var clicked by remember{ mutableStateOf(false) }

    var borderColors= if(clicked) LightBlue else MiddleGrey


    Button(
        onClick = { clicked=!clicked },
        shape= RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        border = BorderStroke(1.dp,borderColors),


    ) {
        Text(text = "${topic}",
            fontSize = 15.sp,
            color = borderColors
        )


    }


}

@Preview
@Composable
fun Preview(){
    SignupSelectTopicsScreen(navController = rememberNavController())
}