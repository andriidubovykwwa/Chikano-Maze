package com.justinale3362.chikanomaze.game.progress

import com.justinale3362.chikanomaze.data.repository.GameSaveData

data class ProgressState(
    val gameSaveData: GameSaveData = GameSaveData()
)
