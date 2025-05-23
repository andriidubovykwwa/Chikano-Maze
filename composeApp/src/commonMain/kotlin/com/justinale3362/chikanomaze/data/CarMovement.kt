package com.justinale3362.chikanomaze.data

data class CarMovement(
    val positions: List<Int>,
    val posIndex: Int = 0,
    val path: Path = Path.DIRECT
) {
    val currentPos = positions[posIndex]
    val length = positions.size
    val faceDirection: Direction
        get() {
            val prevPos = if (path == Path.DIRECT) {
                positions.getOrNull(posIndex - 1) ?: positions.getOrNull(posIndex + 1)
            } else {
                positions.getOrNull(posIndex + 1) ?: positions.getOrNull(posIndex - 1)
            }
            if (prevPos == null) return DEFAULT_FACE_DIRECTION
            return when (currentPos) {
                prevPos + 1 -> {
                    Direction.RIGHT
                }

                prevPos - 1 -> {
                    Direction.LEFT
                }

                prevPos + 10 -> {
                    Direction.DOWN
                }

                prevPos - 10 -> {
                    Direction.UP
                }

                else -> DEFAULT_FACE_DIRECTION
            }
        }

    companion object {
        val DEFAULT_FACE_DIRECTION = Direction.LEFT
        private const val FIELD_WIDTH = 10
    }
}