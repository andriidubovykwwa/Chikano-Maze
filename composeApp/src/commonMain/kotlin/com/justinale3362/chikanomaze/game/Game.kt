package com.justinale3362.chikanomaze.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.justinale3362.chikanomaze.game.loading.LoadingScreen
import com.zambakcahayrican01.echoesofegypt.screen.content.ContentScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Game(viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    when (state.content) {
        null -> LoadingScreen()
        "" -> Navigation()
        else -> ContentScreen(state.content.toString())
    }
}