package com.justinale3362.chikanomaze.data

object LevelGenerator {
    // TODO: lvl1+, lvl2+, lvl3+, lvl4+, lvl5-, lvl6-
    private val levels = listOf(
        """
        e - - - - - c - - -
        d - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        w w w - w w w - - -
        - - - - - - w - - -
        - - - - - - w - - -
        - - - - - - w - - -
        E - - - - e w - - -
        """, // lvl 1
        """
        e g w - - - c - - -
        d - - - - - - - - -
        - g w - - - - - - -
        - - w - - - w w - w
        - - w - - - w - - -
        - g w - - - w - - -
        - - w - e - w - - -
        - - w w w w w - - -
        - - - - - - w - - -
        - - - - c - w - E -
        """, // lvl 2
        """
        e g w - - - - - - -
        d - - - g g g g - -
        - g w d - - - - - -
        - - w - - - w w - w
        - - w - - - w - - -
        - g w - - - w E - -
        - - w - t - w - - -
        - - w w w w w w w w
        - - - - - - w - - -
        - - - - c - w t e -
        """, // lvl 3
        """
        - - - - - w e - - d
        - - - - - w e g - -
        - w w w - w w w - -
        - w d - - - - w g -
        - w w w - w w w - -
        - - - w - w - - - -
        - - t w - w t - - E
        w w w w - w w w w w
        - - - - - - - - - -
        c - - - - - - - - -
        """, // lvl 4
        """
        - - - - - c w d - E
        - - - - - - w - - -
        w - w w w w w - - -
        d - t w t - w - - -
        w w w w - - w - - -
        e - d w - - w - - -
        - - - w - - w - - -
        g - - w - - w - - -
        e - - w - - w - - -
        g - - - - - - - - g
        """, // lvl 5
        """
        d e - - - - d - - g
        - - - - - - - g - -
        - g w w w w w w - g
        - - w E - - d w - -
        - g w w w g - w - -
        - - - - - - - w e -
        - - - - - - - w - -
        w w w w w w w w - -
        - - - - - - - - - d
        c - - - - g - - - -
        """, // lvl 6
    )

    val maxLvl = levels.size

    val dogMovements = listOf(
        listOf(
            CarMovement(listOf(10, 20, 30, 40, 41, 42, 43, 44, 45)),
        ), // lvl 1
        listOf(
            CarMovement(listOf(10, 20, 30, 40, 50, 60, 70, 80, 90)),
        ), // lvl 2
        listOf(
            CarMovement(listOf(10, 20, 30, 40, 50, 60, 70, 80, 90)),
            CarMovement(listOf(23, 24, 25, 26, 27, 28, 29, 19, 9)),
        ), // lvl 3
        listOf(
            CarMovement(listOf(32, 33, 34, 35, 36)),
            CarMovement(listOf(9, 19, 29, 39, 49, 59)),
        ), // lvl 4
        listOf(
            CarMovement(listOf(30, 31)),
            CarMovement(listOf(52, 62, 72, 82, 92)),
            CarMovement(listOf(7, 17, 27, 37, 47, 57, 67, 77, 87, 97)),
        ), // lvl 5
        listOf(
            CarMovement(listOf(0, 10, 20, 30, 40, 50, 60)),
            CarMovement(listOf(89, 88, 87, 86, 85, 84, 83, 82, 81)),
            CarMovement(listOf(6, 7, 8, 18, 28, 38)),
            CarMovement(listOf(36, 46, 56, 66)),
        ), // lvl 6
    )

    val chickenPositions = listOf(
        6, // lvl 1
        94, // lvl 2
        94, // lvl 3
        94, // lvl 4
        5, // lvl 5
        90, // lvl 6
    )

    val movesLimit = listOf(
        28, // lvl 1
        55, // lvl 2
        70, // lvl 3
        45, // lvl 4
        60, // lvl 5
        65, // lvl 6
    )

    fun generateLevel(lvl: Int): List<Cell> {
        return getLevelFromString(levels[lvl - 1])
    }

    private fun getLevelFromString(str: String): List<Cell> {
        val lvlStr = str.replace("\\s".toRegex(), "") // Remove whitespaces
        return lvlStr.map {
            when (it) {
                'w' -> Cell.WALL
                't' -> Cell.TELEPORT
                'e' -> Cell.EGG
                'E' -> Cell.EXIT
                'g' -> Cell.GRASS
                else -> Cell.EMPTY
            }
        }
    }
}