package com.justinale3362.chikanomaze.game.menu

import com.justinale3362.chikanomaze.data.Direction

sealed interface MenuEvent {
    data class SwipeSkin(val direction: Direction) : MenuEvent
    data object InitSave : MenuEvent
}
