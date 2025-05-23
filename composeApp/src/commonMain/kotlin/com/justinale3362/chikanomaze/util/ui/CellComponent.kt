package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.bush
import chikanomaze.composeapp.generated.resources.car
import chikanomaze.composeapp.generated.resources.cell
import chikanomaze.composeapp.generated.resources.egg
import chikanomaze.composeapp.generated.resources.egg_full
import chikanomaze.composeapp.generated.resources.fence
import chikanomaze.composeapp.generated.resources.grass
import chikanomaze.composeapp.generated.resources.house
import chikanomaze.composeapp.generated.resources.tunnel
import chikanomaze.composeapp.generated.resources.underground_way
import chikanomaze.composeapp.generated.resources.wall
import com.justinale3362.chikanomaze.data.CarMovement
import com.justinale3362.chikanomaze.data.Cell
import com.justinale3362.chikanomaze.data.Direction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CellComponent(
    modifier: Modifier = Modifier,
    carMovements: List<CarMovement>,
    cell: Cell,
    index: Int
) {
    Box(
        modifier.aspectRatio(1f)
            .paint(painterResource(Res.drawable.cell), contentScale = ContentScale.FillBounds)
            .shadow(
                elevation = 2.dp,
                ambientColor = Color(0xff00E5FF),
                spotColor = Color(0xff00E5FF)
            ),
        contentAlignment = Alignment.Center
    ) {
        when (cell) {
            Cell.GRASS -> Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(Res.drawable.grass),
                contentDescription = stringResource(Res.string.bush),
                contentScale = ContentScale.FillWidth
            )

            Cell.WALL -> Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(Res.drawable.wall),
                contentDescription = stringResource(Res.string.fence),
                contentScale = ContentScale.FillWidth
            )

            Cell.TELEPORT -> Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(Res.drawable.tunnel),
                contentDescription = stringResource(Res.string.underground_way),
                contentScale = ContentScale.FillWidth
            )

            Cell.CAR -> {
                val faceDirection =
                    carMovements.find { it.currentPos == index }?.faceDirection
                        ?: CarMovement.DEFAULT_FACE_DIRECTION
                Image(
                    modifier = Modifier.fillMaxWidth().graphicsLayer {
                        rotationZ = when (faceDirection) {
                            Direction.UP -> 90f
                            Direction.DOWN -> 270f
                            Direction.LEFT -> 0f
                            Direction.RIGHT -> 180f
                        }
                    },
                    painter = painterResource(Res.drawable.car),
                    contentDescription = stringResource(Res.string.car),
                    contentScale = ContentScale.FillWidth
                )
            }

            Cell.EGG -> Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(Res.drawable.egg_full),
                contentDescription = stringResource(Res.string.egg),
                contentScale = ContentScale.FillHeight
            )

            Cell.EXIT -> Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(Res.drawable.house),
                contentDescription = stringResource(Res.string.house),
                contentScale = ContentScale.FillHeight
            )

            else -> {}
        }
    }
}