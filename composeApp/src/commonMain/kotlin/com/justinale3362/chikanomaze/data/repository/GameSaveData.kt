package com.justinale3362.chikanomaze.data.repository

import com.justinale3362.chikanomaze.data.LevelGenerator
import com.justinale3362.chikanomaze.data.progressItems
import kotlinx.serialization.Serializable

@Serializable
data class GameSaveData(
    val stars: List<Int> = List(LevelGenerator.maxLvl) { 0 },
    val selectedSkinIndex: Int = 0,
) {
    val allStars = stars.sum()

    val availableSkins = if (allStars >= progressItems[4].starsToUnlock) {
        4
    } else if (allStars >= progressItems[2].starsToUnlock) {
        3
    } else if (allStars >= progressItems[0].starsToUnlock) {
        2
    } else 1
}
