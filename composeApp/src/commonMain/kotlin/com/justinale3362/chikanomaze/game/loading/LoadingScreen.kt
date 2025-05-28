package com.justinale3362.chikanomaze.game.loading

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.app_name
import chikanomaze.composeapp.generated.resources.bg
import chikanomaze.composeapp.generated.resources.chicken
import chikanomaze.composeapp.generated.resources.chicken1_front
import chikanomaze.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoadingScreen() {
    val infiniteTransition = rememberInfiniteTransition()
    val logoScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        )
    )
    val chickenOffset by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg),
            contentScale = ContentScale.FillBounds
        )
    ) {
        Image(
            modifier = Modifier.align(Alignment.BottomCenter).height(250.dp).graphicsLayer {
                translationY = chickenOffset * size.height
            },
            painter = painterResource(Res.drawable.chicken1_front),
            contentScale = ContentScale.FillHeight,
            contentDescription = stringResource(Res.string.chicken)
        )
        Box(Modifier.fillMaxSize().safeContentPadding()) {
            Image(
                modifier = Modifier.align(Alignment.TopCenter).width(280.dp).scale(logoScale),
                painter = painterResource(Res.drawable.logo),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(Res.string.app_name)
            )
        }
    }

}