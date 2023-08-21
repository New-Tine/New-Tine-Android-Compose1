package com.example.newtineproject.ui.screens.home.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newtineproject.common.Resource
import com.example.newtineproject.data.remote.NewsRankingApiService
import com.example.newtineproject.data.remote.dto.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val service: NewsRankingApiService
) : ViewModel() {
    
    private val _news = mutableStateOf<Resource<List<News>>?>(null)
    val news: State<Resource<List<News>>?> = _news
    
}