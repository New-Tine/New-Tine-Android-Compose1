package com.example.newtineproject.data.remote.articles_service

import com.example.newtineproject.data.remote.dto.articles.Articles
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface ArticleService {
    suspend fun getArticlesIt(): Articles
    suspend fun getArticlesPolitics(): Articles
    suspend fun getArticlesEconomy(): Articles
    suspend fun getArticlesIndustry(): Articles
    suspend fun getArticlesSociety(): Articles
    suspend fun getArticlesCulture(): Articles
    suspend fun getArticlesSports(): Articles


    companion object {
        fun create(): ArticleService {
            return ArticleServiceImpl(
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