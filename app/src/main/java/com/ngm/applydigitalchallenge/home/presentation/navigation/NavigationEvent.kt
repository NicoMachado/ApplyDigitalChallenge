package com.ngm.applydigitalchallenge.home.presentation.navigation

sealed interface NavigationEvent {
    data class NewsClick(val url: String): NavigationEvent
}
