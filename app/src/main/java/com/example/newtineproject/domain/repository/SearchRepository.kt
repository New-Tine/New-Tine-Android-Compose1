package com.example.newtineproject.domain.repository

import com.example.newtineproject.data.remote.dto.Result

interface SearchRepository {
    suspend fun getNews(): List<Result>
}