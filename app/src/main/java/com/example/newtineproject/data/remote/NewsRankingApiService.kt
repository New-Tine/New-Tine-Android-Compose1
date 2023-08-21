package com.example.newtineproject.data.remote

import com.example.newtineproject.data.remote.dto.Result

interface NewsRankingApiService {
    suspend fun getNews(): List<Result>
}