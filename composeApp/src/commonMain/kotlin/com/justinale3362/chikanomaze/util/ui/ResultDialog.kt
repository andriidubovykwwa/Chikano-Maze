package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.button_bg_red
import chikanomaze.composeapp.generated.resources.completed
import chikanomaze.composeapp.generated.resources.dialog_bg
import chikanomaze.composeapp.generated.resources.empty_star
import chikanomaze.composeapp.generated.resources.failed
import chikanomaze.composeapp.generated.resources.full_star
import chikanomaze.composeapp.generated.resources.menu
import chikanomaze.composeapp.generated.resources.next
import chikanomaze.composeapp.generated.resources.restart
import chikanomaze.composeapp.generated.resources.star_empty
import chikanomaze.composeapp.generated.resources.star_full
import com.justinale3362.chikanomaze.data.LevelGenerator
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ResultDialog(
    modifier: Modifier = Modifier,
    lvl: Int,
    isLevelCompleted: Boolean,
    onContinue: () -> Unit,
    onBackToMenu: () -> Unit,
    stars: Int,
) {
    Dialog(onDismissRequest = {}) {
        Column(
            modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val emptyStars = 3 - stars
                repeat(stars) {
                    Image(
                        modifier = Modifier.width(65.dp),
                        painter = painterResource(Res.drawable.star_full),
                        contentDescription = stringResource(Res.string.full_star),
                        contentScale = ContentScale.FillWidth
                    )
                }
                repeat(emptyStars) {
                    Image(
                        modifier = Modifier.width(65.dp),
                        painter = painterResource(Res.drawable.star_empty),
                        contentDescription = stringResource(Res.string.empty_star),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
            Column(
                Modifier.size(250.dp).paint(
                    painterResource(Res.drawable.dialog_bg),
                    contentScale = ContentScale.FillBounds
                ).padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                GameText(
                    text = "Level $lvl",
                    fontSize = 25.sp,
                    color = Color(0xff7AFCDA)
                )
                GameText(
                    text = stringResource(if (isLevelCompleted) Res.string.completed else Res.string.failed),
                    fontSize = 25.sp,
                    color = Color(0xff7AFCDA)
                )
                if (lvl < LevelGenerator.maxLvl || !isLevelCompleted) {
                    GameButton(
                        Modifier.size(120.dp, 60.dp),
                        text = stringResource(if (isLevelCompleted) Res.string.next else Res.string.restart),
                        fontSize = 20.sp,
                        onClick = { onContinue() },
                        painter = painterResource(Res.drawable.button_bg_red)
                    )
                }
                GameButton(
                    Modifier.size(120.dp, 60.dp),
                    text = stringResource(Res.string.menu),
                    fontSize = 20.sp,
                    onClick = { onBackToMenu() },
                )
            }
        }
    }
}