package com.justinale3362.chikanomaze.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.justinale3362.chikanomaze.util.Orientation
import com.justinale3362.chikanomaze.util.getOrientationController

@Composable
fun Game() {
    LaunchedEffect(Unit) {
        getOrientationController().orientation = Orientation.PORTRAIT
    }

    Navigation()
}