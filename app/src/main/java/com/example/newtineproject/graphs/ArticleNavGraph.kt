package com.example.newtineproject.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.R
import com.example.newtineproject.domain.model.article.Article
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import com.example.newtineproject.ui.screens.home.article.ArticleDetailScreen
import com.example.newtineproject.ui.screens.home.article.ArticleScreen

fun NavGraphBuilder.ArticleNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val articleLists = listOf(
        Article(
            title = "‘노회찬 평전’이 재점화한 ‘좋은 정치’로의 열망\n",
            thumbnail = R.drawable.article13,
            publication = "경향신문",
            timePassed = 1,
            category = "정치",
            contents = "기사 내용~"
        ),
        Article(
            title = "여름휴가 떠난 윤석열 대통령에게 권하는 책 [정치왜그래?]",
            thumbnail = R.drawable.article14,
            publication = "시사IN",
            timePassed = 1,
            category = "정치",
            contents = "기사 내용~"
        ),
        Article(
            title = "'노인 폄하' 김은경 \"철없어 정치언어 몰라\"...野 '전전긍긍'",
            thumbnail = R.drawable.article15,
            publication = "YTN",
            timePassed = 1,
            category = "정치",
            contents = "기사 내용~"
        ),
        Article(
            title = "법으로는 이겼지만 정치에선 지다",
            thumbnail = R.drawable.article16,
            publication = "한국일보",
            timePassed = 1,
            category = "정치",
            contents = "기사 내용~"

        ), Article(
            title = "“이동관 오면 포털 생태계에 정치편향 혹 붙을 공산 크다”",
            thumbnail = R.drawable.article17,
            publication = "미디어 오늘",
            timePassed = 1,
            category = "정치",
            contents = "기사 내용~"
        ), Article(
            title = "정당 현수막 ‘역대급 난립’, 정치 실종 언제까지",
            thumbnail = R.drawable.article18,
            publication = "아시아경제",
            timePassed = 1,
            category = "정치",
            contents = "기사 내용~"
        ), Article(
            title = "민노총, 돌연 잼버리 걸어 文정부 때렸다... “준비 때부터 잇속 논란”",
            thumbnail = R.drawable.article3,
            publication = "조선일보",
            timePassed = 2,
            category = "정치",
            contents = "기사 내용~"
        ), Article(
            title = "‘장애아 엄마’ 나경원 “주호민·교사 양쪽 모두 이해해”",
            thumbnail = R.drawable.article19,
            publication = "디지털 데일리",
            timePassed = 2,
            category = "정치",
            contents = "기사 내용~"
        ), Article(
            title = "남 탓 정치와 내 탓 정치",
            thumbnail = R.drawable.article20,
            publication = "조선일보",
            timePassed = 2,
            category = "정치",
            contents = "기사 내용~"
        ), Article(
            title = "박지원 \"이동관 세더라. 대통령 후보 나오려고 하는 것 아닌가 하는 생각\"",
            thumbnail = R.drawable.article21,
            publication = "프레시안",
            timePassed = 2,
            category = "정치",
            contents = "기사 내용~"
        ), Article(
            title = "공천위해 ‘라인’타지 않아…여야 청년들 ‘소신’ 갖고 뛴다",
            thumbnail = R.drawable.article22,
            publication = "한겨레",
            timePassed = 2,
            category = "정치",
            contents = "기사 내용~"
        ), Article(
            title = "'들러리’ 청년조직은 그만…“정당내 청년정치인 육성시스템 갖춰야”",
            thumbnail = R.drawable.article13,
            publication = "조선일보",
            timePassed = 3,
            category = "정치",
            contents = "기사 내용~"
        )
    )

    navigation(
        route=Graph.ARTICLE,
        startDestination = ArticleScreen.ArticleList.route
    ){
        composable(route = "${HomeDetailScreen.Article.route}/{indexFromDrawer}") { backStackEntry ->
            ArticleScreen(
                navController = navController,
                indexFromDrawer = backStackEntry.arguments?.getString("indexFromDrawer") ?: "0",
                paddingValues = paddingValues
            )
        }

        composable(route="${ArticleScreen.ArticleDetail.route}/{articleIndex}"){ backStackEntry->

            ArticleDetailScreen(
                navController = navController,
                articleIndex = backStackEntry.arguments?.getString("articleIndex")?.toInt()?:0,
                articleList = articleLists)
        }


    }
}



sealed class ArticleScreen(val route:String){
    data object ArticleList: ArticleScreen(route="ArticleList")
    data object ArticleDetail: ArticleScreen(route="ArticleDetail")
}