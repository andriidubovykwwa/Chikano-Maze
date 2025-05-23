package com.justinale3362.chikanomaze.game.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.bg
import chikanomaze.composeapp.generated.resources.collected_egg
import chikanomaze.composeapp.generated.resources.egg_empty
import chikanomaze.composeapp.generated.resources.egg_full
import chikanomaze.composeapp.generated.resources.uncollected_egg
import com.justinale3362.chikanomaze.game.Screen
import com.justinale3362.chikanomaze.util.ui.BackButton
import com.justinale3362.chikanomaze.util.ui.ControlPanel
import com.justinale3362.chikanomaze.util.ui.FieldComponent
import com.justinale3362.chikanomaze.util.ui.GameText
import com.justinale3362.chikanomaze.util.ui.ResultDialog
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent
    val safePaddingValues = WindowInsets.safeContent.asPaddingValues()

    LaunchedEffect(state.isLevelCompleted) {
        if (state.isLevelEnded) onEvent(MainEvent.RegisterCompletedLvl)
    }

    Column(
        Modifier.fillMaxSize()
            .paint(painterResource(Res.drawable.bg), contentScale = ContentScale.FillBounds),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                top = safePaddingValues.calculateTopPadding(),
                start = safePaddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = safePaddingValues.calculateEndPadding(LayoutDirection.Ltr)
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(Modifier.size(50.dp)) {
                navController.popBackStack(
                    Screen.Menu,
                    inclusive = false
                )
            }
            GameText(
                text = state.movesLeft.toString(),
                fontSize = 30.sp,
                color = Color(0xff7AFCDA)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                repeat(2) {
                    Image(
                        modifier = Modifier.width(35.dp),
                        painter = painterResource(if (state.eggs > it) Res.drawable.egg_full else Res.drawable.egg_empty),
                        contentDescription = stringResource(if (state.eggs > it) Res.string.collected_egg else Res.string.uncollected_egg),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
        FieldComponent(
            Modifier.fillMaxWidth().padding(
                start = safePaddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = safePaddingValues.calculateEndPadding(LayoutDirection.Ltr)
            ),
            field = state.gameField,
            fieldWidth = state.fieldWidth,
            chickenPos = state.chickenPos,
            carMovements = state.carMovements,
            chickenFaceDirection = state.chickenFaceDirection,
            skinIndex = state.skinIndex
        )
        ControlPanel(
            Modifier.height(215.dp).fillMaxWidth(),
            onMove = { onEvent(MainEvent.MoveChicken(it)) },
            onSkipTurn = { onEvent(MainEvent.SkipTurn) },
            onUseAcceleration = { onEvent(MainEvent.UseAcceleration) },
            accelerations = state.accelerations,
            paddingValues = PaddingValues(
                bottom = safePaddingValues.calculateBottomPadding(),
                start = safePaddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = safePaddingValues.calculateEndPadding(LayoutDirection.Ltr),
            ),
            accelrationMoves = state.accelerationMovesLeft
        )
    }
    if (state.isLevelEnded) {
        ResultDialog(
            lvl = state.lvl,
            isLevelCompleted = state.isLevelCompleted,
            stars = state.stars,
            onContinue = { onEvent(MainEvent.Continue) },
            onBackToMenu = { navController.popBackStack(Screen.Menu, inclusive = false) }
        )
    }
}