package com.justinale3362.chikanomaze.game.level_selection

import com.justinale3362.chikanomaze.data.repository.GameSaveData

data class LevelSelectionState(
    val gameSaveData: GameSaveData = GameSaveData()
) {
    val maxUnlockedLevel: Int
        get() {
            val firstUncompleted = gameSaveData.stars.indexOfFirst { it == 0 }
            return if (firstUncompleted >= 0) firstUncompleted + 1
            else gameSaveData.stars.size
        }
}
