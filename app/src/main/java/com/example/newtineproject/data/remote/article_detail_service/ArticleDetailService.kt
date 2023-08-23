package com.example.newtineproject.data.remote.article_detail_service

import com.example.newtineproject.data.remote.dto.article_detail.ArticleDetail
import com.example.newtineproject.data.remote.news_ranking_service.NewsRankingService
import com.example.newtineproject.data.remote.news_ranking_service.NewsRankingServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface ArticleDetailService {

    suspend fun getEachArticleDetail(newsId: Long): ArticleDetail

    companion object {
        fun create(): ArticleDetailService {
            return ArticleDetailServiceImpl(
                client = HttpClient(Android) {
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                    }
                }
            )
        }
    }
}
