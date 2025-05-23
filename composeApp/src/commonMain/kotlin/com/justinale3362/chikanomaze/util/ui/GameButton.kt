package com.justinale3362.chikanomaze.util.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.button_bg_blue
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 25.sp,
    onClick: () -> Unit,
    painter: Painter = painterResource(Res.drawable.button_bg_blue)
) {
    Box(
        modifier
            .paint(painter, contentScale = ContentScale.FillBounds)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        GameText(text = text, fontSize = fontSize)
    }
}