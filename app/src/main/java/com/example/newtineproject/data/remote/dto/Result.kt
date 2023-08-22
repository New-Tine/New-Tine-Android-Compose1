package com.example.newtineproject.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String
)