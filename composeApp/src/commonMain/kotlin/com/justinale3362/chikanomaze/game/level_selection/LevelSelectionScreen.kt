package com.justinale3362.chikanomaze.game.level_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.bg
import chikanomaze.composeapp.generated.resources.level_locked
import chikanomaze.composeapp.generated.resources.select_level
import com.justinale3362.chikanomaze.game.Screen
import com.justinale3362.chikanomaze.util.ui.BackButton
import com.justinale3362.chikanomaze.util.ui.GameText
import com.justinale3362.chikanomaze.util.ui.LevelSelectorButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LevelSelectionScreen(
    navController: NavController,
    viewModel: LevelSelectionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(
        Modifier.fillMaxSize()
            .paint(painterResource(Res.drawable.bg), contentScale = ContentScale.FillBounds)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = WindowInsets.safeContent.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    Modifier.fillMaxWidth(),
                ) {
                    BackButton(
                        Modifier.size(50.dp).align(Alignment.CenterStart)
                    ) { navController.popBackStack() }
                }
            }
            item {
                GameText(
                    Modifier.align(Alignment.Center),
                    text = stringResource(Res.string.select_level),
                    fontSize = 40.sp,
                    color = Color(0xffFFFFFF)
                )
            }
            itemsIndexed(state.gameSaveData.stars) { index, stars ->
                val lvl = index + 1
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (lvl == state.maxUnlockedLevel + 1) {
                        GameText(
                            modifier = Modifier.padding(top = 20.dp),
                            text = stringResource(Res.string.level_locked),
                            fontSize = 40.sp,
                            color = Color(0xffFFFFFF)
                        )
                    }
                    LevelSelectorButton(
                        Modifier.size(320.dp, 70.dp),
                        lvl = lvl,
                        stars = stars,
                        onClick = { navController.navigate(Screen.Main(lvl)) },
                        isUnlocked = lvl <= state.maxUnlockedLevel
                    )
                }
            }
        }
    }
}
