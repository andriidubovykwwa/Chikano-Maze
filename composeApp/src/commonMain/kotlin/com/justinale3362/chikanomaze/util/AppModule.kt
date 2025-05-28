package com.justinale3362.chikanomaze.util

import com.justinale3362.chikanomaze.data.repository.getGameSaveRepository
import com.justinale3362.chikanomaze.game.GameViewModel
import com.justinale3362.chikanomaze.game.content.ContentViewModel
import com.justinale3362.chikanomaze.game.level_selection.LevelSelectionViewModel
import com.justinale3362.chikanomaze.game.main.MainViewModel
import com.justinale3362.chikanomaze.game.menu.MenuViewModel
import com.justinale3362.chikanomaze.game.progress.ProgressViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { getGameSaveRepository() }
    viewModel { MainViewModel(get(), get()) }
    viewModel { ProgressViewModel(get()) }
    viewModel { LevelSelectionViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { ContentViewModel(get()) }
    viewModel { GameViewModel(get()) }
}