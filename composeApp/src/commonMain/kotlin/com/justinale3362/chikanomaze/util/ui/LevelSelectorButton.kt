package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.empty_star
import chikanomaze.composeapp.generated.resources.full_star
import chikanomaze.composeapp.generated.resources.level
import chikanomaze.composeapp.generated.resources.level_button_bg_dark_green
import chikanomaze.composeapp.generated.resources.level_button_bg_green
import chikanomaze.composeapp.generated.resources.star_empty
import chikanomaze.composeapp.generated.resources.star_full
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LevelSelectorButton(
    modifier: Modifier = Modifier,
    lvl: Int,
    stars: Int,
    onClick: () -> Unit,
    isUnlocked: Boolean
) {
    Box(
        modifier
            .paint(
                painter = painterResource(if (isUnlocked) Res.drawable.level_button_bg_green else Res.drawable.level_button_bg_dark_green),
                contentScale = ContentScale.FillBounds
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(horizontal = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameText(text = "${stringResource(Res.string.level)} $lvl", fontSize = 25.sp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val emptyStars = 3 - stars
                repeat(stars) {
                    Image(
                        modifier = Modifier.width(40.dp),
                        painter = painterResource(Res.drawable.star_full),
                        contentDescription = stringResource(Res.string.full_star),
                        contentScale = ContentScale.FillWidth
                    )
                }
                repeat(emptyStars) {
                    Image(
                        modifier = Modifier.width(40.dp),
                        painter = painterResource(Res.drawable.star_empty),
                        contentDescription = stringResource(Res.string.empty_star),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}