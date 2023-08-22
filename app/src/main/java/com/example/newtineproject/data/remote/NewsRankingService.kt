package com.example.newtineproject.data.remote

import com.example.newtineproject.data.remote.dto.NewsRanking
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface NewsRankingService {
    suspend fun getNews(): NewsRanking

    companion object {
        fun create(): NewsRankingService {
            return NewsRankingServiceImpl(
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