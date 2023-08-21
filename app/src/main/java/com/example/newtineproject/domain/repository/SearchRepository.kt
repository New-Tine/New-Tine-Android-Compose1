package com.example.newtineproject.domain.repository

import com.example.newtineproject.data.remote.dto.News

interface SearchRepository {
    suspend fun getNews(): List<News>
}