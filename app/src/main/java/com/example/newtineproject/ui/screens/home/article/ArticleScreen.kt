package com.example.newtineproject.ui.screens.home.article

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.CustomScrollableTabRow
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.newtineproject.R
import com.example.newtineproject.common.RippleDetail
import com.example.newtineproject.domain.model.article.Article
import com.example.newtineproject.domain.model.home.Category
import com.example.newtineproject.ui.screens.home.article.components.ArticleItem
import com.example.newtineproject.ui.screens.home.article.components.ArticleTopAppBar
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGray
import kotlinx.coroutines.launch

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
fun ArticleScreen(
    navController: NavController,
    indexFromDrawer: String,
    paddingValues: PaddingValues
) {

    val categories = Category.values().map { it.categoryName }
    val pagerState = rememberPagerState(
        pageCount = { categories.size },
        initialPage = indexFromDrawer.toInt()
    )
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            ArticleTopAppBar(navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                content = {
                    CustomScrollableTabRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        edgePadding = 17.dp,
                        selectedTabIndex = pagerState.currentPage,
                        divider = {},
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                                color = LightBlue // Change the color of the selected tab's bar here
                            )
                        },
                        tabs = {
                            categories.forEachIndexed { index, title ->
                                Tab(
                                    modifier = Modifier.padding(horizontal = 9.dp),
                                    selected = pagerState.currentPage == index,
                                    selectedContentColor = LightBlue,
                                    unselectedContentColor = LightGray,
                                    interactionSource = RippleDetail.NoRippleInteractionSource(),
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically, // 텍스트를 수직 중앙 정렬
                                        horizontalArrangement = Arrangement.Center, // 텍스트를 수평 중앙 정렬
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = title,
                                            fontSize = 14.sp
                                        )
                                    }
                                }

                            }
                        }
                    )
                    Text(
                        modifier = Modifier.padding(start = 15.dp, top = 13.dp),
                        text = "Today News \"HOT\" Topic",
                        style = LocalTextStyle.current.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                    HorizontalPager(
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

@Composable
fun CategoryNewsItScreen() {
    val articleLists = listOf(
        Article(
            title = "필즈상 수상 1주년…'허준이 수학\n" +
                    "난제 연구소' 모레 연다",
            thumbnail = R.drawable.article1,
            publication = "연합뉴스",
            timePassed = 1,
        ),
        Article(
            title = "세계 첫 모바일 길잡이 티맵...22년 만에 가입자 2000만 명 태웠다",
            thumbnail = R.drawable.article2,
            publication = "연합뉴스",
            timePassed = 1,
        ),
        Article(
            title = "네이버 마켓에 이미지 솔루션 내놨더니 \"매출 3배 껑충\"",
            thumbnail = R.drawable.article3,
            publication = "연합뉴스",
            timePassed = 2
        ),
        Article(
            title = "지질연, 호주 퀸스랜드와 핵심광물 공동연구 추진",
            thumbnail = R.drawable.article4,
            publication = "한국일보",
            timePassed = 2
        ),
        Article(
            title = "사우디 왕세자가 반길 희소식, \n" +
                    "MS-블리자드 인수 청신호",
            thumbnail = R.drawable.article5,
            publication = "전자신문",
            timePassed = 3
        ),
        Article(
            title = "길 잃은 수제맥주…프리미엄 가치 앞세워 경쟁력 회복해야",
            thumbnail = R.drawable.article6,
            publication = "아시아경제",
            timePassed = 3
        ),
        Article(
            title = "트위터 잡는다던 스레드 '출시효과 반짝'이었나…이용자 70% 급감",
            thumbnail = R.drawable.article7,
            publication = "한국경제",
            timePassed = 3,
        ),
        Article(
            title = "사우디 아라비아 왕세자가 반길 \n" +
                    "희소식, MS-블리자드 인수 청신호",
            thumbnail = R.drawable.article8,
            publication = "디지털 데일리",
            timePassed = 4
        ),
        Article(
            title = "환경부 \"테슬라 모델Y, 전기차 보조금 전액 받기 어려워\"",
            thumbnail = R.drawable.article9,
            publication = "연합뉴스",
            timePassed = 5
        ),
        Article(
            title = "\"경영보다 컴공 전공자 필요\"…IT 신입채용 두배 늘린 한은\n",
            thumbnail = R.drawable.article10,
            publication = "서울경제신문",
            timePassed = 5
        ),
        Article(
            title = "‘프론트엔드냐, 백엔드냐’를 고민하는 당신에게\n",
            thumbnail = R.drawable.article11,
            publication = "요즘IT",
            timePassed = 5,
        ),
        Article(
            title = "하이투자 “삼성전기 삼화콘덴서 포함 MLCC업체, IT용 부품 재고조정 일단락”",
            thumbnail = R.drawable.article12,
            publication = "Business Post",
            timePassed = 5,
        )
    )
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
    ) {
        items(articleLists) { article ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ArticleItem(article = article, onItemClick = {})
            }
            if (articleLists.indexOf(article) != articleLists.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 17.dp))
            }
        }
    }

}

@Composable
fun CategoryNewsPoliticScreen() {
    val articleLists = listOf(
        Article(
            title = "‘노회찬 평전’이 재점화한 ‘좋은 정치’로의 열망\n",
            thumbnail = R.drawable.article13,
            publication = "경향신문",
            timePassed = 1,
        ),
        Article(
            title = "여름휴가 떠난 윤석열 대통령에게 권하는 책 [정치왜그래?]",
            thumbnail = R.drawable.article14,
            publication = "시사IN",
            timePassed = 1,
        ),
        Article(
            title = "'노인 폄하' 김은경 \"철없어 정치언어 몰라\"...野 '전전긍긍'",
            thumbnail = R.drawable.article15,
            publication = "YTN",
            timePassed = 1,
        ),
        Article(
            title = "법으로는 이겼지만 정치에선 지다",
            thumbnail = R.drawable.article16,
            publication = "한국일보",
            timePassed = 1
        ), Article(
            title = "“이동관 오면 포털 생태계에 정치편향 혹 붙을 공산 크다”",
            thumbnail = R.drawable.article17,
            publication = "미디어 오늘",
            timePassed = 1
        ), Article(
            title = "정당 현수막 ‘역대급 난립’, 정치 실종 언제까지",
            thumbnail = R.drawable.article18,
            publication = "아시아경제",
            timePassed = 1
        ), Article(
            title = "민노총, 돌연 잼버리 걸어 文정부 때렸다... “준비 때부터 잇속 논란”",
            thumbnail = R.drawable.article3,
            publication = "조선일보",
            timePassed = 2,
        ), Article(
            title = "‘장애아 엄마’ 나경원 “주호민·교사 양쪽 모두 이해해”",
            thumbnail = R.drawable.article19,
            publication = "디지털 데일리",
            timePassed = 2
        ), Article(
            title = "남 탓 정치와 내 탓 정치",
            thumbnail = R.drawable.article20,
            publication = "조선일보",
            timePassed = 2
        ), Article(
            title = "박지원 \"이동관 세더라. 대통령 후보 나오려고 하는 것 아닌가 하는 생각\"",
            thumbnail = R.drawable.article21,
            publication = "프레시안",
            timePassed = 2
        ), Article(
            title = "공천위해 ‘라인’타지 않아…여야 청년들 ‘소신’ 갖고 뛴다",
            thumbnail = R.drawable.article22,
            publication = "한겨레",
            timePassed = 2,
        ), Article(
            title = "'들러리’ 청년조직은 그만…“정당내 청년정치인 육성시스템 갖춰야”",
            thumbnail = R.drawable.article13,
            publication = "조선일보",
            timePassed = 3,
        )
    )
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
    ) {
        items(articleLists) { article ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ArticleItem(article = article, onItemClick = {})
            }
            if (articleLists.indexOf(article) != articleLists.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 17.dp))
            }
        }
    }

}
