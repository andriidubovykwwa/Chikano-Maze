package com.justinale3362.chikanomaze.game.main

import com.justinale3362.chikanomaze.data.CarMovement
import com.justinale3362.chikanomaze.data.Cell
import com.justinale3362.chikanomaze.data.Direction
import com.justinale3362.chikanomaze.data.LevelGenerator
import kotlin.math.abs

data class MainState(
    val fieldWidth: Int = 10,
    val lvl: Int = 1,
    val gameField: List<Cell> = LevelGenerator.generateLevel(lvl),
    val carMovements: List<CarMovement> = emptyList(),
    val chickenPos: Int = 0,
    val eggs: Int = 0,
    val movesLeft: Int = 30,
    val accelerations: Int = 2,
    val accelerationMovesLeft: Int = 0,
    val chickenFaceDirection: Direction = Direction.DOWN,
    val skinIndex: Int = 0,
) {
    val isLevelCompleted: Boolean = gameField[chickenPos] == Cell.EXIT
    val stars = (if (isLevelCompleted) 1 else 0) + eggs

    private val isLevelFailed: Boolean
        get() {
            if (accelerationMovesLeft > 0) return false
            if (movesLeft <= 0) return true
            if (gameField[chickenPos] == Cell.GRASS) return false // No fail in grass
            // If dog in radius 2 - fail
            for (dy in -2..2) {
                for (dx in -2..2) {
                    // Exclude corners where the sum of distances exceeds the radius
                    if (abs(dx) + abs(dy) > 2) continue

                    val newPos = chickenPos + dx + dy * fieldWidth

                    // Check if newPos is within valid indices and within the same row group
                    if (newPos in gameField.indices &&
                        abs((newPos % fieldWidth) - (chickenPos % fieldWidth)) <= 2 &&
                        gameField[newPos] == Cell.CAR &&
                        hasClearPath(chickenPos, newPos)
                    ) return true
                }
            }
            return false
        }

    val isLevelEnded = isLevelCompleted || isLevelFailed

    fun canChickenMove(direction: Direction): Boolean {
        val targetIndex = getNeighbor(chickenPos, direction) ?: return false
        val targetCell = gameField[targetIndex]
        return targetCell.isWalkable
    }

    fun hasClearPath(start: Int, end: Int): Boolean {
        val startX = start % fieldWidth
        val startY = start / fieldWidth
        val endX = end % fieldWidth
        val endY = end / fieldWidth

        // Use Bresenham's line algorithm to trace the path between start and end
        var x = startX
        var y = startY
        val dx = abs(endX - startX)
        val dy = abs(endY - startY)
        val sx = if (startX < endX) 1 else -1
        val sy = if (startY < endY) 1 else -1
        var err = dx - dy

        while (x != endX || y != endY) {
            val currentPos = y * fieldWidth + x
            if (gameField[currentPos] == Cell.WALL) return false

            val e2 = 2 * err
            if (e2 > -dy) {
                err -= dy
                x += sx
            }
            if (e2 < dx) {
                err += dx
                y += sy
            }
        }

        return true
    }


    fun getNeighbor(index: Int, direction: Direction): Int? {
        return when (direction) {
            Direction.UP -> if (index >= fieldWidth) index - fieldWidth else null
            Direction.DOWN -> if (index + fieldWidth < gameField.size) index + fieldWidth else null
            Direction.LEFT -> if (index % fieldWidth > 0) index - 1 else null
            Direction.RIGHT -> if (index % fieldWidth < fieldWidth - 1) index + 1 else null
        }
    }

    companion object {
        const val ACCELERATION_MOVES_DEFAULT = 4
        const val DEFAULT_ACCELERATIONS = 1
    }

}
