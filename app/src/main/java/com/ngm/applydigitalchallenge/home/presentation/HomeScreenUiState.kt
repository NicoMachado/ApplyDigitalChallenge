package com.ngm.applydigitalchallenge.home.presentation

import com.ngm.applydigitalchallenge.home.domain.model.News


data class HomeScreenUiState (
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val newsFeed: List<News> = emptyList(),
    val onNewsClick: (String) -> Unit = {}
)
