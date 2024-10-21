package com.ngm.applydigitalchallenge.home.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ngm.applydigitalchallenge.home.presentation.components.NewsListItem
import com.ngm.applydigitalchallenge.home.presentation.components.PullToRefreshLazyColumn
import com.ngm.applydigitalchallenge.home.presentation.components.SwipeToDeleteBox
import com.ngm.applydigitalchallenge.home.presentation.navigation.NavigationEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@SuppressLint("NewApi")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = get(),
    onNavigationEvent: (NavigationEvent) -> Unit = {}
) {
    val uiState = viewModel.uiState
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            viewModel.onEvent(HomeScreenUiEvent.onPullToRefresh)
            delay(500)
            isRefreshing = false
        }
    }

    PullToRefreshLazyColumn(
        items = uiState.newsFeed,
        isRefreshingOrLoading = isRefreshing || uiState.isLoading,
        onRefresh = onRefresh,
        content = { newsItem ->
            SwipeToDeleteBox(
                onDelete = {
                    viewModel.onEvent(HomeScreenUiEvent.onSwipeToDelete(newsItem.id))
                },
            ) {
                NewsListItem(newsItem, onNavigationEvent)
            }
        },
    )

    if (uiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

}





