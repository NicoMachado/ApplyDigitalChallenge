package com.ngm.applydigitalchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ngm.applydigitalchallenge.home.presentation.navigation.MainNavigation
import com.ngm.applydigitalchallenge.ui.theme.ApplyDigitalChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ApplyDigitalChallengeTheme {
                MainNavigation()
            }
        }
    }
}
