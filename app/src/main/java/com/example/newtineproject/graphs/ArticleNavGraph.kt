package com.example.newtineproject.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newtineproject.domain.model.article.Article
import com.example.newtineproject.graphs.navigation_bar_items.HomeDetailScreen
import com.example.newtineproject.ui.screens.home.article.ArticleDetailScreen
import com.example.newtineproject.ui.screens.home.article.ArticleScreen

fun NavGraphBuilder.ArticleNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
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
                navController = navController, newsId = backStackEntry.arguments?.getLong("articleIndex")?:0
                )
        }


    }
}



sealed class ArticleScreen(val route:String){
    data object ArticleList: ArticleScreen(route="ArticleList")
    data object ArticleDetail: ArticleScreen(route="ArticleDetail")
}