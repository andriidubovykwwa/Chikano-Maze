package com.justinale3362.chikanomaze.data

enum class Cell(val isWalkable: Boolean = false) {
    EMPTY(isWalkable = true),
    GRASS(isWalkable = true),
    WALL,
    TELEPORT(isWalkable = true),
    EXIT(isWalkable = true),
    CAR,
    EGG(isWalkable = true)
}