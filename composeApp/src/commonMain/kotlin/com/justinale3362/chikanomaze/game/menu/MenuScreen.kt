package com.justinale3362.chikanomaze.game.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.app_name
import chikanomaze.composeapp.generated.resources.bg
import chikanomaze.composeapp.generated.resources.button_bg_red
import chikanomaze.composeapp.generated.resources.logo
import chikanomaze.composeapp.generated.resources.play
import chikanomaze.composeapp.generated.resources.progress
import chikanomaze.composeapp.generated.resources.tutorial
import com.justinale3362.chikanomaze.game.Screen
import com.justinale3362.chikanomaze.util.ui.GameButton
import com.justinale3362.chikanomaze.util.ui.SkinSelector
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MenuScreen(navController: NavController, viewModel: MenuViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent

    LaunchedEffect(Unit) {
        onEvent(MenuEvent.InitSave)
    }

    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg),
            contentScale = ContentScale.FillBounds
        ).safeContentPadding()
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(0.58f),
                painter = painterResource(Res.drawable.logo),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(Res.string.app_name)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                GameButton(
                    Modifier.size(180.dp, 90.dp),
                    text = stringResource(Res.string.play),
                    onClick = { navController.navigate(Screen.LevelSelection) },
                    painter = painterResource(Res.drawable.button_bg_red)
                )
                GameButton(
                    Modifier.size(180.dp, 90.dp),
                    text = stringResource(Res.string.progress),
                    onClick = { navController.navigate(Screen.Progress) })
                GameButton(
                    Modifier.size(180.dp, 90.dp),
                    text = stringResource(Res.string.tutorial),
                    onClick = { navController.navigate(Screen.Tutorial) })
            }
            SkinSelector(
                Modifier.fillMaxWidth(),
                selectedSkinIndex = state.gameSaveData.selectedSkinIndex,
                availableSkins = state.gameSaveData.availableSkins,
                onSwipeSkin = { onEvent(MenuEvent.SwipeSkin(it)) }
            )
        }
    }
}
