package com.justinale3362.chikanomaze.game.main

import com.justinale3362.chikanomaze.data.Direction

sealed interface MainEvent {
    data class MoveChicken(val direction: Direction) : MainEvent
    data object SkipTurn : MainEvent
    data object UseAcceleration : MainEvent
    data object Continue : MainEvent
    data object RegisterCompletedLvl : MainEvent
}