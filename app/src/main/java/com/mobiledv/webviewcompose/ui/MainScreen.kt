package com.mobiledv.webviewcompose.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.*

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MainScreen() {
    val url by remember {
        mutableStateOf("https://google.com")
    }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    var text by remember(state.content.getCurrentUrl()) {
        mutableStateOf(state.content.getCurrentUrl() ?: "")
    }

    Column {
        TopAppBar {
            IconButton(onClick = {
                navigator.navigateBack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
            IconButton(onClick = { navigator.navigateForward() }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
            }

            Text(
                text = "Web browser", style = TextStyle(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = MaterialTheme.typography.h6.fontWeight,
                )
            )
        }
        Row(modifier = Modifier.padding(12.dp)) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(
                    9f
                ),
                maxLines = 1
            )
            if (state.errorsForCurrentRequest.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Warning, contentDescription = "",
                    modifier = Modifier.weight(
                        1f,
                    ),
                    tint = Color.Red,
                )
            }
        }

        var loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth()
            )
        }

        val webClient = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("Accompanist WebView", "Page started loading for $url")
                }
            }
        }

        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = webClient
        )
    }
}