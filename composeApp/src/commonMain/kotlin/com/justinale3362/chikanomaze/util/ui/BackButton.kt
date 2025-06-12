package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.back_button
import org.jetbrains.compose.resources.painterResource

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier
            .paint(
                painter = painterResource(Res.drawable.back_button),
                contentScale = ContentScale.FillBounds
            )
            .clip(CircleShape)
            .clickable { onClick() },
    )
}