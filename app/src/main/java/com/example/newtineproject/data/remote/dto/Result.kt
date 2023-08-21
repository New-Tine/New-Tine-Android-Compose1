package com.example.newtineproject.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val id: Int,
    val title: String
)