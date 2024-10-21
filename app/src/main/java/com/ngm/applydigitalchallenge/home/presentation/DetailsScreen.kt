package com.ngm.applydigitalchallenge.home.presentation

import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    webViewUrl: Uri?,
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            windowInsets = WindowInsets(top = 0.dp),
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            title = { Text(text = "Back") }
        )

        // Verificar si el URL es válido
        webViewUrl?.let { url ->
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = WebViewClient() // Evitar abrir el navegador externo
                        settings.javaScriptEnabled = true // Habilitar JavaScript si es necesario
                        loadUrl(url.toString()) // Cargar la URL en el WebView
                    }
                },
                update = { webView ->
                    webView.loadUrl(url.toString()) // Actualizar el WebView si cambia la URL
                }
            )
        }
    }

}
