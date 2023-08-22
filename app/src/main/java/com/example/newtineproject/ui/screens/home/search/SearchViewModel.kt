package com.example.newtineproject.ui.screens.home.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newtineproject.common.Resource
import com.example.newtineproject.data.remote.NewsRankingService
import com.example.newtineproject.data.remote.dto.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val service: NewsRankingService
) : ViewModel() {
    
    private val _news = mutableStateOf<Resource<List<Result>>?>(null)
    val news: State<Resource<List<Result>>?> = _news
    
}