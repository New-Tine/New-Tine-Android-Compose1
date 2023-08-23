package com.example.newtineproject.ui.screens.home.article

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.newtineproject.common.RippleDetail
import com.example.newtineproject.data.remote.articles_service.ArticleService
import com.example.newtineproject.data.remote.dto.articles.Articles
import com.example.newtineproject.domain.model.article.Article
import com.example.newtineproject.domain.model.home.Category
import com.example.newtineproject.ui.screens.home.article.components.ArticleItem
import com.example.newtineproject.ui.screens.home.article.components.ArticleTopAppBar
import com.example.newtineproject.ui.theme.LightBlue
import com.example.newtineproject.ui.theme.LightGray
import kotlinx.coroutines.launch


val service = ArticleService.create()

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
        pageCount = { Category.values().size },
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
                                color = LightBlue
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
                            0 -> {CategoryNewsItScreen(navController)}
                            1 -> {CategoryNewsPoliticScreen(navController)}
                            2 -> {CategoryNewsEconomyScreen(navController = navController)}
                            3 -> {CategoryNewsIndustryScreen(navController = navController)}
                            4 -> {CategoryNewsSocietyScreen(navController = navController)}
                            5 -> {CategoryNewsCultureScreen(navController = navController)}
                            else -> {CategoryNewsSportsScreen(navController = navController)}
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun CategoryNewsItScreen(navController: NavController) {

    val articles = produceState(
        initialValue = Articles(
            code = 0,
            isSuccess = false,
            message = "",
            result = emptyList()
        ),
        producer = {
            value = service.getArticlesIt()
        }
    )

    val articleLists = articles.value.result.mapIndexed { index, result ->
        Article(
            title = result.title,
            thumbnail = result.imgUrl,
            publication = result.pressName,
            timePassed = index + 1,
            category = "IT",
            contents = "기사 내용~"
        )
    }

    val listState = rememberLazyListState()


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
    ) {
        items(articleLists.size) { index ->
            val article = articleLists[index]

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ArticleItem(article = article, onItemClick = {
                    Log.d("index",index.toString())
                    navController.navigate("${com.example.newtineproject.graphs.ArticleScreen.ArticleDetail.route}/$index")

                })
            }

            if (index != articleLists.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 17.dp))
            }
        }
    }
}

@Composable
fun CategoryNewsPoliticScreen(navController: NavController) {
    val articles = produceState(
        initialValue = Articles(
            code = 0,
            isSuccess = false,
            message = "",
            result = emptyList()
        ),
        producer = {
            value = service.getArticlesPolitics()
        }
    )

    val articleLists = articles.value.result.mapIndexed { index, result ->
        Article(
            title = result.title,
            thumbnail = result.imgUrl,
            publication = result.pressName,
            timePassed = index + 1,
            category = "IT",
            contents = "기사 내용~"
        )
    }
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
fun CategoryNewsEconomyScreen(navController: NavController) {
    val articles = produceState(
        initialValue = Articles(
            code = 0,
            isSuccess = false,
            message = "",
            result = emptyList()
        ),
        producer = {
            value = service.getArticlesEconomy()
        }
    )

    val articleLists = articles.value.result.mapIndexed { index, result ->
        Article(
            title = result.title,
            thumbnail = result.imgUrl,
            publication = result.pressName,
            timePassed = index + 1,
            category = "IT",
            contents = "기사 내용~"
        )
    }
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
fun CategoryNewsIndustryScreen(navController: NavController) {
    val articles = produceState(
        initialValue = Articles(
            code = 0,
            isSuccess = false,
            message = "",
            result = emptyList()
        ),
        producer = {
            value = service.getArticlesIndustry()
        }
    )

    val articleLists = articles.value.result.mapIndexed { index, result ->
        Article(
            title = result.title,
            thumbnail = result.imgUrl,
            publication = result.pressName,
            timePassed = index + 1,
            category = "IT",
            contents = "기사 내용~"
        )
    }
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
fun CategoryNewsSocietyScreen(navController: NavController) {
    val articles = produceState(
        initialValue = Articles(
            code = 0,
            isSuccess = false,
            message = "",
            result = emptyList()
        ),
        producer = {
            value = service.getArticlesSociety()
        }
    )

    val articleLists = articles.value.result.mapIndexed { index, result ->
        Article(
            title = result.title,
            thumbnail = result.imgUrl,
            publication = result.pressName,
            timePassed = index + 1,
            category = "IT",
            contents = "기사 내용~"
        )
    }
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
fun CategoryNewsCultureScreen(navController: NavController) {
    val articles = produceState(
        initialValue = Articles(
            code = 0,
            isSuccess = false,
            message = "",
            result = emptyList()
        ),
        producer = {
            value = service.getArticlesCulture()
        }
    )

    val articleLists = articles.value.result.mapIndexed { index, result ->
        Article(
            title = result.title,
            thumbnail = result.imgUrl,
            publication = result.pressName,
            timePassed = index + 1,
            category = "IT",
            contents = "기사 내용~"
        )
    }
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
fun CategoryNewsSportsScreen(navController: NavController) {
    val articles = produceState(
        initialValue = Articles(
            code = 0,
            isSuccess = false,
            message = "",
            result = emptyList()
        ),
        producer = {
            value = service.getArticlesSports()
        }
    )

    val articleLists = articles.value.result.mapIndexed { index, result ->
        Article(
            title = result.title,
            thumbnail = result.imgUrl,
            publication = result.pressName,
            timePassed = index + 1,
            category = "IT",
            contents = "기사 내용~"
        )
    }
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