package com.example.newtineproject.ui.screens.mypage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.mypage.UserProfile
import com.example.newtineproject.graphs.navigation_bar_items.NewTechDetailScreen
import com.example.newtineproject.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPageScreen(
    navController: NavController,
    userProfile: UserProfile
) {
    Scaffold(
        modifier = Modifier
            .background(color = Color.White),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "마이페이지") }
            )
        },
        content = {padding ->
            Column(
                modifier = Modifier
                    .padding(
                        top = padding.calculateTopPadding() + 4.dp
                        )
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.newtine_logo),
                            contentDescription = "newtine logo",
                            modifier = Modifier
                                .width(35.dp),
                            alignment = Alignment.Center

                        )
                        Text(
                            text = userProfile.daysOfUsing.toString() + "일째 뉴틴 중",
                            style = LocalTextStyle.current.copy(
                                color = LightBlue,
                                fontSize = 20.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                        Text(
                            text = userProfile.userName + " 님 환영해요!",
                            style = LocalTextStyle.current.copy(
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.userpic),
                        contentDescription = "userImage",
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(
                        onClick = {
                            //교환권 구매하기 클릭 시 이동하는 화면
                        },
                        modifier= Modifier
                            .width(190.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(LightBlue),
                        shape = RoundedCornerShape(8.dp)
                    ){
                        Row(
                            modifier = Modifier.padding(end = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.voucher),
                                contentDescription = "gift voucher",
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(55.dp)
                                    .padding(start = 0.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(text = "교환권 구매",
                                fontSize = 18.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight(700)
                            )
                        }

                    }

                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = {
                            //포인트 클릭 시 이동하는 화면
                        },
                        modifier= Modifier
                            .width(160.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFF3F4F6)),
                        shape = RoundedCornerShape(8.dp)
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.coin),
                                contentDescription = "coin",
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(65.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(text = userProfile.userCointPoints.toString()+" p",
                                fontSize = 18.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }


                    }
                }

            }
        }
    )

}





