package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.justinale3362.chikanomaze.data.CarMovement
import com.justinale3362.chikanomaze.data.Cell
import com.justinale3362.chikanomaze.data.Direction

@Composable
fun FieldComponent(
    modifier: Modifier = Modifier,
    field: List<Cell>,
    fieldWidth: Int,
    chickenPos: Int,
    carMovements: List<CarMovement>,
    chickenFaceDirection: Direction,
    skinIndex: Int
) {
    val fieldHeight = field.size / fieldWidth
    val spacing = 0.dp
    Column(modifier.aspectRatio(1f), verticalArrangement = Arrangement.spacedBy(spacing)) {
        repeat(fieldHeight) { y ->
            Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(spacing)) {
                repeat(fieldWidth) { x ->
                    val i = y * fieldWidth + x
                    val cell = field[i]
                    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        CellComponent(
                            Modifier.matchParentSize(),
                            cell = cell,
                            carMovements = carMovements,
                            index = i
                        )
                        if (i == chickenPos) {
                            ChickenComponent(
                                Modifier.matchParentSize()
                                    .alpha(if (cell == Cell.GRASS) 0.5f else 1f),
                                chickenFaceDirection = chickenFaceDirection,
                                skinIndex = skinIndex
                            )
                        }
                    }
                }
            }
        }
    }
}