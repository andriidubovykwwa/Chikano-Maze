package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.blue_square
import chikanomaze.composeapp.generated.resources.full_star
import chikanomaze.composeapp.generated.resources.level_button_bg_blue
import chikanomaze.composeapp.generated.resources.lock
import chikanomaze.composeapp.generated.resources.locked
import chikanomaze.composeapp.generated.resources.star_full
import com.justinale3362.chikanomaze.data.ProgressItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProgressItemComponent(
    modifier: Modifier = Modifier,
    progressItem: ProgressItem,
    isUnlocked: Boolean
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            Modifier.fillMaxHeight().weight(1f).paint(
                painter = painterResource(Res.drawable.level_button_bg_blue),
                contentScale = ContentScale.FillBounds
            ).padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                GameText(text = progressItem.starsToUnlock.toString(), fontSize = 20.sp)
            }
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.width(40.dp),
                    painter = painterResource(Res.drawable.star_full),
                    contentDescription = stringResource(Res.string.full_star),
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(Modifier.weight(5f), contentAlignment = Alignment.Center) {
                GameText(
                    text = if (isUnlocked) progressItem.description else stringResource(Res.string.locked),
                    fontSize = 17.sp
                )
            }
        }
        Box(
            Modifier.fillMaxHeight().aspectRatio(1f).paint(
                painter = painterResource(Res.drawable.blue_square),
                contentScale = ContentScale.FillBounds
            ).padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.height(50.dp),
                painter = painterResource(if (isUnlocked) progressItem.image else Res.drawable.lock),
                contentDescription = progressItem.description,
                contentScale = ContentScale.FillHeight
            )
        }
    }
}
