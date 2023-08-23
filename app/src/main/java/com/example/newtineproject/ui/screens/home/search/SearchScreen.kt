package com.example.newtineproject.ui.screens.home.search

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.InputChipDefaults.inputChipBorder
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newtineproject.R
import com.example.newtineproject.data.remote.news_ranking_service.NewsRankingService
import com.example.newtineproject.data.remote.dto.news_ranking.NewsRanking
import com.example.newtineproject.domain.model.search.RealtimeArticle
import com.example.newtineproject.domain.model.search.RecentViewedArticle
import com.example.newtineproject.domain.model.search.Recommendation
import com.example.newtineproject.ui.screens.home.search.components.RealtimeArticleItem
import com.example.newtineproject.ui.screens.home.search.components.RecentViewedItem
import com.example.newtineproject.ui.screens.home.search.components.RecommendedArticleItem
import com.example.newtineproject.ui.theme.SearchBarColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val recentHistoryItems = remember {
        mutableStateListOf("뉴스")
    }
    var selected by remember { mutableStateOf(false) }

    val service = NewsRankingService.create()

    val news = produceState(
        initialValue = NewsRanking(
            code = 0,
            isSuccess = false,
            message = "",
            result = emptyList()
        ),
        producer = {
            value = service.getNews()
        }
    )

    val realtimeArticleList = news.value.result.mapIndexed { index, newsItem ->
        val iconResId = when {
            index % 3 == 0 -> R.drawable.realtime_article_arrow_up
            index % 3 == 1 -> R.drawable.realtime_article_new
            else -> R.drawable.realtime_article_arrow_down
        }

        RealtimeArticle(
            number = index + 1,
            icon = iconResId,
            title = newsItem.title
        )
    }

    Scaffold(

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            //SearchBar
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (active) 0.dp else 16.dp)
                    .animateContentSize()
                    .background(Color.White),
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    recentHistoryItems.add(text)
                    text = ""
                    active = false

                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(
                        text = "Search news",
                        style = LocalTextStyle.current.copy(
                            color = Color(0xFF9CA3AF)
                        )
                    )
                },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Search news"
                        )
                    }
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (text.isNotEmpty()) {
                                    text = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                },
                colors = SearchBarDefaults.colors(containerColor = SearchBarColor)
            ) {
                recentHistoryItems.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(horizontal = 16.dp, vertical = 18.dp)
                    ){
                        Icon(
                            painter = painterResource(R.drawable.search_history),
                            contentDescription = "search_history"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = it)
                    }
                    Divider()
                }
            }
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "최근 검색어",
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Start
            ){
                recentHistoryItems.forEach {text->
                    InputChip(
                        modifier = Modifier
                            .height(35.dp),
                        selected = selected,
                        onClick = {
                            /* go somewhere */
                        },
                        label = {
                            Text(
                                text = text,
                                modifier = Modifier.padding(start = 4.dp),
                                style = LocalTextStyle.current.copy(
                                    color = Color.Gray,
                                    fontWeight = FontWeight(400),
                                    textAlign = TextAlign.Center
                                )
                            ) },
                        shape = RoundedCornerShape(18.dp),
                        border = inputChipBorder(
                            borderColor = Color.LightGray,
                            borderWidth = 1.dp
                        ),
                        colors = InputChipDefaults.inputChipColors(Color.White),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    recentHistoryItems.remove(text)
                                }
                            ) {
                                Icon(
                                    //chip has to be removed once clicked
                                    imageVector = Icons.Default.Close,
                                    tint = Color.LightGray,
                                    contentDescription = "Close Chip",
                                    modifier = Modifier.size(InputChipDefaults.AvatarSize),
                                )
                            }

                        }
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(11.dp)
                    .background(color = Color(0xFFF3F4F6))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "추천 뉴스",
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            LazyColumn(
                contentPadding = it,
                modifier = Modifier
                    .padding(horizontal = 23.dp)
            ){
                items(recommendationList){recommendation ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        RecommendedArticleItem(
                            recommendation = recommendation,
                            onItemClick = {}
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "실시간 인기 뉴스",
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            LazyColumn(
                contentPadding = it,
                modifier = Modifier
                    .padding(horizontal = 23.dp)
            ){
                items(realtimeArticleList){ realtimeArticle ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        RealtimeArticleItem(
                            realtimeArticle = realtimeArticle,
                            onItemClick = {}
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "최근 본 뉴스",
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                contentPadding = it,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
            ){
                items(recentViewedList){ recentViewedArticle ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ){
                        RecentViewedItem(
                            recentViewedArticle = recentViewedArticle,
                            onItemClick = {}
                        )
                    }

                }
            }
        }
    }
}

val recommendationList = listOf(
    Recommendation(
        title = "파업 일주일 앞…\"항공대란 막아라\"..",
        publication = "한국일보"
    ),
    Recommendation(
        title = "긴급대출·만기연장…금융업계, 수해 지원 ..",
        publication = "연합뉴스"
    ),
    Recommendation(
        title = "테슬라, 첫 전기 트럭 생산 소식에 개장 전 ..",
        publication = "한국경제"
    ),
    Recommendation(
        title = "\"해외 휴가 비행기 못 타나요?\"…아시아 ..",
        publication = "연합뉴스TV"
    )

)

val recentViewedList = listOf(
    RecentViewedArticle(
        thumbnail = R.drawable.article10,
        title = "한국 가계빚 증가 속도 세계 2위",
        publication = "파이낸셜 뉴스"
    ),
    RecentViewedArticle(
        thumbnail = R.drawable.article11,
        title = "'극한 호우'에 여의도 94배 논밭 잠겼다 …",
        publication = "매일경제"
    ),
    RecentViewedArticle(
        thumbnail = R.drawable.article12,
        title = "2023 국제해양영화제, 부산서 21일 개막",
        publication = "중앙일보"
    ),
    RecentViewedArticle(
        thumbnail = R.drawable.article13,
        title = "한국 가계빚 증가 속도 세계 2위",
        publication = "파이낸셜 뉴스"
    ),


    )
