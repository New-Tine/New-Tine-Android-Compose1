package com.example.newtineproject.data.remote

import com.example.newtineproject.common.Resource
import com.example.newtineproject.common.Url
import com.example.newtineproject.data.remote.dto.News
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class NewsRankingApiServiceImpl @Inject constructor(
    private val client: HttpClient
): NewsRankingApiService {
    override suspend fun getNews(): Resource<List<News>> {
        return client.get(Url.SEARCH_RANKING).body()
    }
}