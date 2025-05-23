package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.acceleration
import chikanomaze.composeapp.generated.resources.bottom_panel
import chikanomaze.composeapp.generated.resources.down
import chikanomaze.composeapp.generated.resources.left
import chikanomaze.composeapp.generated.resources.move_arrow
import chikanomaze.composeapp.generated.resources.right
import chikanomaze.composeapp.generated.resources.skip_button
import chikanomaze.composeapp.generated.resources.skip_turn
import chikanomaze.composeapp.generated.resources.up
import com.justinale3362.chikanomaze.data.Direction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ControlPanel(
    modifier: Modifier = Modifier,
    onSkipTurn: () -> Unit,
    onUseAcceleration: () -> Unit,
    onMove: (Direction) -> Unit,
    accelrationMoves: Int,
    accelerations: Int,
    paddingValues: PaddingValues
) {
    Row(
        modifier.paint(
            painter = painterResource(Res.drawable.bottom_panel),
            contentScale = ContentScale.FillBounds
        ).padding(paddingValues),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.width(60.dp).clickable { onSkipTurn() },
            painter = painterResource(Res.drawable.skip_button),
            contentDescription = stringResource(Res.string.skip_turn),
            contentScale = ContentScale.FillWidth,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(60.dp).offset(y = 10.dp).rotate(270f)
                    .clickable { onMove(Direction.UP) },
                painter = painterResource(Res.drawable.move_arrow),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(Res.string.up)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    modifier = Modifier.width(60.dp).rotate(180f)
                        .clickable { onMove(Direction.LEFT) },
                    painter = painterResource(Res.drawable.move_arrow),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = stringResource(Res.string.left)
                )
                Image(
                    modifier = Modifier.width(60.dp).clickable { onMove(Direction.RIGHT) },
                    painter = painterResource(Res.drawable.move_arrow),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = stringResource(Res.string.right)
                )
            }
            Image(
                modifier = Modifier.width(60.dp).offset(y = (-10).dp).rotate(90f)
                    .clickable { onMove(Direction.DOWN) },
                painter = painterResource(Res.drawable.move_arrow),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(Res.string.down)
            )
        }
        Box(
            modifier = Modifier.width(60.dp)
                .rotate(if (accelrationMoves > 0) 15f else 0f)
                .scale(if (accelrationMoves > 0) 1.15f else 1f)
                .clickable(enabled = accelerations > 0) { onUseAcceleration() }
        ) {
            Image(
                modifier = Modifier.fillMaxWidth()
                    .clickable(enabled = accelerations > 0) { onUseAcceleration() },
                painter = painterResource(Res.drawable.acceleration),
                contentDescription = stringResource(Res.string.acceleration),
                contentScale = ContentScale.FillWidth,
            )
            GameText(
                modifier = Modifier.align(Alignment.TopEnd),
                text = if (accelrationMoves > 0) accelrationMoves.toString() else "${accelerations}x",
                fontSize = 22.sp,
                color = Color(0xff7AFCDA)
            )
        }
    }
}