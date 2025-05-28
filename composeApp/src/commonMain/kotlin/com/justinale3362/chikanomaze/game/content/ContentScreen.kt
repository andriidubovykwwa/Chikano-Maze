package com.zambakcahayrican01.echoesofegypt.screen.content

import androidx.compose.runtime.Composable
import com.justinale3362.chikanomaze.game.content.ContentViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
expect fun ContentScreen(content: String, viewModel: ContentViewModel = koinViewModel())