package com.zambakcahayrican01.echoesofegypt.screen.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.justinale3362.chikanomaze.game.content.ContentViewModel
import com.justinale3362.chikanomaze.util.ContentInterceptor
import com.justinale3362.chikanomaze.util.PopupManager
import com.justinale3362.chikanomaze.util.ui.ContentBackButton
import com.justinale3362.chikanomaze.util.ui.ContentDisplayer
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState

@Composable
actual fun ContentScreen(content: String, viewModel: ContentViewModel) {
    val state = viewModel.state.collectAsState().value
    val navigator = rememberWebViewNavigator(requestInterceptor = ContentInterceptor())
    var popupManager by remember { mutableStateOf<PopupManager?>(null) }
    val webState = rememberWebViewState(content)
    LaunchedEffect(webState.loadingState) {
        if (state.isSaved) return@LaunchedEffect
        if (webState.loadingState != LoadingState.Finished) return@LaunchedEffect
        if (webState.lastLoadedUrl == null) return@LaunchedEffect
        viewModel.save(webState.lastLoadedUrl ?: "")
    }
    Box(Modifier.fillMaxSize().background(Color.Black)) {
        ContentDisplayer(
            Modifier.fillMaxSize(),
            state = webState,
            navigator = navigator,
            onPopupManagerInit = { popupManager = it },
            onAddPopup = viewModel::addPopup,
        )
        ContentBackButton(
            Modifier.align(Alignment.TopStart),
            isEnabled = navigator.canGoBack || state.hasPopup
        ) {
            if (!state.hasPopup) {
                navigator.navigateBack()
            } else {
                popupManager?.closeAllPopus()
                viewModel.closeAllPopups()
            }
        }
    }
}