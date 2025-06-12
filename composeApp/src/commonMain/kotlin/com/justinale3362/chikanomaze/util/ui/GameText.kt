package com.justinale3362.chikanomaze.util.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.baloo2
import org.jetbrains.compose.resources.Font

@Composable
fun GameText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontSize: TextUnit = 22.sp
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(Res.font.baloo2)),
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(4f, 4f),
                blurRadius = 8f
            )
        )
    )

}