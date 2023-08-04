package com.example.newtineproject.ui.screens.article


import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.CategoryNews
import com.example.newtineproject.ui.screens.article.components.CategoryNewsItem
import com.example.newtineproject.ui.theme.LightBlue
import kotlinx.coroutines.launch

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, )
fun ArticleScreen(navController: NavController) {
    val list = listOf("IT/과학", "정치", "경제", "산업", "사회", "문화", "스포츠")
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go Back",
                            tint = Color.Gray
                        )
                    }
                },
                title = { Text(
                    text = "기사"
                ) },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.LightGray
                        )
                    }
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Gray
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                content = {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        containerColor = Color.White,
                        divider = {},
                        modifier = Modifier
                            .padding(horizontal = 17.dp),
                        tabs = {
                            list.forEachIndexed { index, title ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically, // 텍스트를 수직 중앙 정렬
                                        horizontalArrangement = Arrangement.Center, // 텍스트를 수평 중앙 정렬
                                        modifier = Modifier.padding(8.dp),
                                    ) {
                                        Text(
                                            text = title,
                                            fontSize = 11.sp,
                                            color = if (pagerState.currentPage == index) LightBlue else Color.LightGray,
                                            modifier = Modifier
                                                .fillMaxWidth() // 텍스트가 가로로 꽉 차도록 설정
                                        )
                                    }
                                }

                            }
                        }
                    )

                    HorizontalPager(
                        pageCount = list.size,
                        modifier = Modifier.fillMaxSize(),
                        state = pagerState
                    ) { tabId ->
                        when (tabId) {
                            0 -> CategoryNewsItScreen()
                            1 -> CategoryNewsPoliticScreen()
                            else -> CategoryNewsItScreen()
                        }

                    }
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryNewsItScreen(){
    val categoryNewsList = listOf(
        CategoryNews(
            title = "필즈상 수상 1주년…'허준이 수학\n" +
                    "난제 연구소' 모레 연다",
            thumbnail = R.drawable._000759433_001_20230717150801084_2__1_,
            publication = "연합뉴스",
            timePassed = 1,
        ),
        CategoryNews(
            title = "세계 첫 모바일 길잡이 티맵...22년 만에 가입자 2000만 명 태웠다",
            thumbnail = R.drawable._000759433_001_20230717150801084_2__2_,
            publication = "연합뉴스",
            timePassed = 1,
        ),
        CategoryNews(
            title = "네이버 마켓에 이미지 솔루션 내놨더니 \"매출 3배 껑충\"",
            thumbnail = R.drawable._000759433_001_20230717150801084_2__3_,
            publication = "연합뉴스",
            timePassed = 2
        ),
        CategoryNews(
            title = "지질연, 호주 퀸스랜드와 핵심광물 공동연구 추진",
            thumbnail = R.drawable._000759433_001_20230717150801084_2__4_,
            publication = "한국일보",
            timePassed = 2
        ),CategoryNews(
            title = "사우디 왕세자가 반길 희소식, \n" +
                    "MS-블리자드 인수 청신호",
            thumbnail = R.drawable._000759433_001_20230717150801084_2__5_,
            publication = "전자신문",
            timePassed = 3
        ),CategoryNews(
            title = "길 잃은 수제맥주…프리미엄 가치 앞세워 경쟁력 회복해야",
            thumbnail = R.drawable._y_2_1,
            publication = "아시아경제",
            timePassed = 3
        ),CategoryNews(
            title = "트위터 잡는다던 스레드 '출시효과 반짝'이었나…이용자 70% 급감",
            thumbnail = R.drawable._y_2_1__1_,
            publication = "한국경제",
            timePassed = 3,
        ),CategoryNews(
            title = "사우디 아라비아 왕세자가 반길 \n" +
                    "희소식, MS-블리자드 인수 청신호",
            thumbnail = R.drawable._y_2_1__2_,
            publication = "디지털 데일리",
            timePassed = 4
        ),CategoryNews(
            title = "환경부 \"테슬라 모델Y, 전기차 보조금 전액 받기 어려워\"",
            thumbnail = R.drawable._y_2_1__3_,
            publication = "연합뉴스",
            timePassed = 5
        ),CategoryNews(
            title = "\"경영보다 컴공 전공자 필요\"…IT 신입채용 두배 늘린 한은\n",
            thumbnail = R.drawable._9t9j56eby_1,
            publication = "서울경제신문",
            timePassed = 5
        ),CategoryNews(
            title = "‘프론트엔드냐, 백엔드냐’를 고민하는 당신에게\n",
            thumbnail = R.drawable.frontend,
            publication = "요즘IT",
            timePassed = 5,
        ),CategoryNews(
            title = "하이투자 “삼성전기 삼화콘덴서 포함 MLCC업체, IT용 부품 재고조정 일단락”",
            thumbnail = R.drawable._0230511104156_24812,
            publication = "Business Post",
            timePassed = 5,
        )
    )
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        state = listState,
    ) {
        items(categoryNewsList) { categoryNews ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ){
                CategoryNewsItem(categoryNews = categoryNews, onItemClick = {})
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryNewsPoliticScreen(){
    val categoryNewsList = listOf(
        CategoryNews(
            title = "‘노회찬 평전’이 재점화한 ‘좋은 정치’로의 열망\n",
            thumbnail = R.drawable.p12,
            publication = "경향신문",
            timePassed = 1,
        ),
        CategoryNews(
            title = "여름휴가 떠난 윤석열 대통령에게 권하는 책 [정치왜그래?]",
            thumbnail = R.drawable.p2,
            publication = "시사IN",
            timePassed = 1,
        ),
        CategoryNews(
            title = "'노인 폄하' 김은경 \"철없어 정치언어 몰라\"...野 '전전긍긍'",
            thumbnail = R.drawable.p3,
            publication = "YTN",
            timePassed = 1,
        ),
        CategoryNews(
            title = "법으로는 이겼지만 정치에선 지다",
            thumbnail = R.drawable.p4,
            publication = "한국일보",
            timePassed = 1
        ),CategoryNews(
            title = "“이동관 오면 포털 생태계에 정치편향 혹 붙을 공산 크다”",
            thumbnail = R.drawable.p5,
            publication = "미디어 오늘",
            timePassed = 1
        ),CategoryNews(
            title = "정당 현수막 ‘역대급 난립’, 정치 실종 언제까지",
            thumbnail = R.drawable.p6,
            publication = "아시아경제",
            timePassed = 1
        ),CategoryNews(
            title = "민노총, 돌연 잼버리 걸어 文정부 때렸다... “준비 때부터 잇속 논란”",
            thumbnail = R.drawable._000759433_001_20230717150801084_2__3_,
            publication = "조선일보",
            timePassed = 2,
        ),CategoryNews(
            title = "‘장애아 엄마’ 나경원 “주호민·교사 양쪽 모두 이해해”",
            thumbnail = R.drawable.p8,
            publication = "디지털 데일리",
            timePassed = 2
        ),CategoryNews(
            title = "남 탓 정치와 내 탓 정치",
            thumbnail = R.drawable.p9,
            publication = "조선일보",
            timePassed = 2
        ),CategoryNews(
            title = "박지원 \"이동관 세더라. 대통령 후보 나오려고 하는 것 아닌가 하는 생각\"",
            thumbnail = R.drawable.p10,
            publication = "프레시안",
            timePassed = 2
        ),CategoryNews(
            title = "공천위해 ‘라인’타지 않아…여야 청년들 ‘소신’ 갖고 뛴다",
            thumbnail = R.drawable.p11,
            publication = "한겨레",
            timePassed = 2,
        ),CategoryNews(
            title = "'들러리’ 청년조직은 그만…“정당내 청년정치인 육성시스템 갖춰야”",
            thumbnail = R.drawable.p12,
            publication = "조선일보",
            timePassed = 3,
        )
    )
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        state = listState,
    ) {
        items(categoryNewsList) { categoryNews ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ){
                CategoryNewsItem(categoryNews = categoryNews, onItemClick = {})
            }
        }
    }

}
