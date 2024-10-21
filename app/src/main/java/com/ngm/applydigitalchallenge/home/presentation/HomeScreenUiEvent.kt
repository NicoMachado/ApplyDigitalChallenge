package com.ngm.applydigitalchallenge.home.presentation

sealed interface HomeScreenUiEvent {
    data class onSwipeToDelete(val id: Int): HomeScreenUiEvent
    data object onPullToRefresh: HomeScreenUiEvent
}
