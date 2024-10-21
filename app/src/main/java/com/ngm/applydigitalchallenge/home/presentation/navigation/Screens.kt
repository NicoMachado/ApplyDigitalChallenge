package com.ngm.applydigitalchallenge.home.presentation.navigation

sealed class Screens(val route: String) {
    object Main: Screens("main")
    object Details: Screens("details")
}
