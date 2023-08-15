package com.example.newtineproject.ui.screens.mypage

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CustomScrollableTabRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.mypage.UserProfile
import com.example.newtineproject.ui.screens.mypage.components.myPageActivity
import com.example.newtineproject.ui.screens.mypage.components.myPageHelp
import com.example.newtineproject.ui.screens.mypage.components.myPageSetting
import com.example.newtineproject.ui.theme.LightBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPageScreen(
    navController: NavController,
    userProfile: UserProfile
) {
    val myPageCategoryList = listOf("활동 내역","설정","도움")
    val pagerState = rememberPagerState(
        pageCount = {3},
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .background(color = Color.White),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "마이페이지") },
                modifier = Modifier.background(Color.White)
            )
        },
        content = {padding ->
            Column(
                modifier = Modifier
                    .padding(
                        top = padding.calculateTopPadding() + 4.dp
                    )
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.weight(3f),
                    content = {
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
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.width(145.dp)
                                ){
                                    Image(
                                        painter = painterResource(id = R.drawable.newtine_logo),
                                        contentDescription = "newtine logo",
                                        modifier = Modifier
                                            .width(35.dp),
                                        alignment = Alignment.Center,
                                        )
                                }
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
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Image(
                                        painter = painterResource(id = R.drawable.voucher),
                                        contentDescription = "gift voucher",
                                        modifier = Modifier
                                            .width(39.dp)
                                            .height(44.dp)
                                            .padding(start = 0.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
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
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Image(
                                        painter = painterResource(id = R.drawable.coin),
                                        contentDescription = "coin",
                                        modifier = Modifier
                                            .width(30.dp)
                                            .height(35.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = userProfile.userCointPoints.toString()+" p",
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(top = 25.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "관심사 키워드",
                                style = LocalTextStyle.current.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color.Black
                                )
                            )

                            ClickableText(
                                text = AnnotatedString("수정하기"),
                                onClick = {
                                    //interestKeyword 수정하는 화면
                                },
                                style = LocalTextStyle.current.copy(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF9CA3AF),
                                    letterSpacing = 0.43.sp
                                )
                            )
                        }
                        FlowRow(
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                                .padding(top = 20.dp)
                                .padding(bottom = 20.dp)
                        ){
                            userProfile.interestKeyword.forEach{
                                SuggestionChip(
                                    modifier = Modifier
                                        .padding(horizontal = 2.dp),
                                    onClick = {
                                        //관심키워드로 검색 결과 창 이동
                                    },
                                    label = {
                                        Text(
                                            text = it.invoke(),
                                            modifier = Modifier.padding(vertical = 4.dp),
                                            style = LocalTextStyle.current.copy(
                                                color = Color(0xFF9CA3AF),
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(400),
                                                textAlign = TextAlign.Center
                                            )
                                        )
                                    },
                                    shape = RoundedCornerShape(18.dp),
                                    border = SuggestionChipDefaults.suggestionChipBorder(
                                        borderColor = Color.LightGray,
                                        borderWidth = 1.dp
                                    ),
                                    colors = SuggestionChipDefaults.suggestionChipColors(
                                        Color.White
                                    )
                                )
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .background(color = Color(0xFFF3F4F6))
                        )
                    }
                )

                Column(
                    modifier = Modifier.weight(2f),
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            CustomScrollableTabRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White),
                                edgePadding = 17.dp,
                                selectedTabIndex = pagerState.currentPage,
                                divider = {},
                                indicator = { tabPositions ->
                                    TabRowDefaults.Indicator(
                                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                                        color = LightBlue
                                    )
                                },
                                tabs = {
                                    myPageCategoryList.forEachIndexed{ index, title ->
                                        Tab(
                                            modifier = Modifier
                                                .background(Color.White)
                                                .padding(horizontal = 8.dp),
                                            selected = pagerState.currentPage == index,
                                            selectedContentColor = LightBlue,
                                            unselectedContentColor = Color.LightGray,
                                            onClick = {
                                                coroutineScope.launch {
                                                    pagerState.scrollToPage(index)
                                                }
                                            }
                                        ){
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(Color.White)
                                                    .padding(vertical = 8.dp)
                                            ){
                                                Text(
                                                    text = title,
                                                    fontSize = 16.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                            HorizontalPager(
                                modifier = Modifier.fillMaxWidth(),
                                state = pagerState,
                                verticalAlignment = Alignment.Top
                            ) {tabId ->
                                when(tabId){
                                    0 -> {
                                        myPageActivity()}
                                    1 -> {
                                        myPageSetting()}
                                    2 -> {
                                        myPageHelp()}
                                }

                            }
                        }
                    }
                )
            }

        }
    )

}







