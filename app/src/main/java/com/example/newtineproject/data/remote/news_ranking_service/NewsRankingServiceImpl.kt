package com.example.newtineproject.data.remote.news_ranking_service

import com.example.newtineproject.common.Url
import com.example.newtineproject.data.remote.dto.news_ranking.NewsRanking
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NewsRankingServiceImpl(
    private val client: HttpClient
): NewsRankingService {
    override suspend fun getNews(): NewsRanking {
        return client.get(Url.NEWS_RANKING).body()
    }
}