package com.ngm.applydigitalchallenge.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngm.applydigitalchallenge.home.domain.repository.HackerNewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repository: HackerNewsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    var uiState by mutableStateOf(HomeScreenUiState())
        private set

    init {
        loadNews()
    }

    private fun loadNews(isPullToRefresh: Boolean = false) {
        uiState = uiState.copy(
            isLoading = !isPullToRefresh,
        )
        viewModelScope.launch(dispatcher) {
            val localdata = repository.getNews()
            uiState = uiState.copy(
                newsFeed = localdata,
                isLoading = false,
            )
            println("Success ${localdata.size}")
        }
    }


    fun onEvent(event: HomeScreenUiEvent) {
        when (event) {
            is HomeScreenUiEvent.onSwipeToDelete -> {
                viewModelScope.launch {
                    repository.deleteNews(event.id)
                    loadNews(false)
                }
            }

            HomeScreenUiEvent.onPullToRefresh -> {
                loadNews(true)
            }
        }
    }
}