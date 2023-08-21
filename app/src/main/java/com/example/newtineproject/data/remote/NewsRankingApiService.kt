package com.example.newtineproject.data.remote

import com.example.newtineproject.common.Resource
import com.example.newtineproject.data.remote.dto.News

interface NewsRankingApiService {
    suspend fun getNews(): Resource<List<News>>
}