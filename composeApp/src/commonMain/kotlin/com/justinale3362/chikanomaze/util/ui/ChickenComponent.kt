package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.chicken
import chikanomaze.composeapp.generated.resources.chicken1_front
import chikanomaze.composeapp.generated.resources.chicken1_side
import chikanomaze.composeapp.generated.resources.chicken2_front
import chikanomaze.composeapp.generated.resources.chicken2_side
import chikanomaze.composeapp.generated.resources.chicken3_front
import chikanomaze.composeapp.generated.resources.chicken3_side
import chikanomaze.composeapp.generated.resources.chicken4_front
import chikanomaze.composeapp.generated.resources.chicken4_side
import com.justinale3362.chikanomaze.data.Direction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChickenComponent(
    modifier: Modifier = Modifier,
    chickenFaceDirection: Direction,
    skinIndex: Int
) {
    Box(modifier.aspectRatio(1f), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.fillMaxHeight().graphicsLayer {
                scaleX = if (chickenFaceDirection == Direction.LEFT) -1f else 1f
            },
            painter = painterResource(
                when (skinIndex) {
                    0 -> when (chickenFaceDirection) {
                        Direction.UP -> Res.drawable.chicken1_side
                        Direction.DOWN -> Res.drawable.chicken1_front
                        Direction.LEFT -> Res.drawable.chicken1_side
                        Direction.RIGHT -> Res.drawable.chicken1_side
                    }

                    1 -> when (chickenFaceDirection) {
                        Direction.UP -> Res.drawable.chicken2_side
                        Direction.DOWN -> Res.drawable.chicken2_front
                        Direction.LEFT -> Res.drawable.chicken2_side
                        Direction.RIGHT -> Res.drawable.chicken2_side
                    }

                    2 -> when (chickenFaceDirection) {
                        Direction.UP -> Res.drawable.chicken3_side
                        Direction.DOWN -> Res.drawable.chicken3_front
                        Direction.LEFT -> Res.drawable.chicken3_side
                        Direction.RIGHT -> Res.drawable.chicken3_side
                    }

                    3 -> when (chickenFaceDirection) {
                        Direction.UP -> Res.drawable.chicken4_side
                        Direction.DOWN -> Res.drawable.chicken4_front
                        Direction.LEFT -> Res.drawable.chicken4_side
                        Direction.RIGHT -> Res.drawable.chicken4_side
                    }

                    else -> when (chickenFaceDirection) {
                        Direction.UP -> Res.drawable.chicken1_side
                        Direction.DOWN -> Res.drawable.chicken1_front
                        Direction.LEFT -> Res.drawable.chicken1_side
                        Direction.RIGHT -> Res.drawable.chicken1_side
                    }
                }

            ),
            contentDescription = stringResource(Res.string.chicken),
            contentScale = ContentScale.FillHeight
        )
    }
}