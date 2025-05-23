package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.chicken
import chikanomaze.composeapp.generated.resources.chicken1_front
import chikanomaze.composeapp.generated.resources.chicken2_front
import chikanomaze.composeapp.generated.resources.chicken3_front
import chikanomaze.composeapp.generated.resources.chicken4_front
import chikanomaze.composeapp.generated.resources.left
import chikanomaze.composeapp.generated.resources.move_arrow
import chikanomaze.composeapp.generated.resources.right
import com.justinale3362.chikanomaze.data.Direction
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SkinSelector(
    modifier: Modifier = Modifier,
    selectedSkinIndex: Int,
    availableSkins: Int,
    onSwipeSkin: (Direction) -> Unit
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (availableSkins > 1) {
            Image(
                modifier = Modifier.width(60.dp).rotate(180f)
                    .clickable { onSwipeSkin(Direction.LEFT) },
                painter = painterResource(Res.drawable.move_arrow),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(Res.string.left)
            )
        }
        Image(
            modifier = Modifier.height(180.dp),
            painter = painterResource(
                when (selectedSkinIndex) {
                    0 -> Res.drawable.chicken1_front
                    1 -> Res.drawable.chicken2_front
                    2 -> Res.drawable.chicken3_front
                    3 -> Res.drawable.chicken4_front
                    else -> Res.drawable.chicken1_front
                }
            ),
            contentScale = ContentScale.FillHeight,
            contentDescription = stringResource(Res.string.chicken)
        )
        if (availableSkins > 1) {
            Image(
                modifier = Modifier.width(60.dp).clickable { onSwipeSkin(Direction.RIGHT) },
                painter = painterResource(Res.drawable.move_arrow),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(Res.string.right)
            )
        }
    }
}
