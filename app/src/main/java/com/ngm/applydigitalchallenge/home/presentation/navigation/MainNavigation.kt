package com.ngm.applydigitalchallenge.home.presentation.navigation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ngm.applydigitalchallenge.home.presentation.DetailsScreen
import com.ngm.applydigitalchallenge.home.presentation.MainScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            route = "root",
            startDestination = Screens.Main.route
        ) {
            composable(Screens.Main.route) { backStackEntry ->
                MainScreen(
                    onNavigationEvent = {
                        when (it) {
                            is NavigationEvent.NewsClick -> {
                                backStackEntry.savedStateHandle["news_url"] = it.url
                                navController.navigate(Screens.Details.route)
                            }
                        }
                    }
                )
            }
            composable(
                route = Screens.Details.route
            ) {
                val webviewUrl: Uri? =
                    navController.previousBackStackEntry?.savedStateHandle?.get<String>("news_url")?.let {
                        Uri.parse(it)
                    }
                DetailsScreen(
                    webViewUrl = webviewUrl,
                    onBack = {
                        navController.popBackStack()
                    })
            }
        }
    }
}

