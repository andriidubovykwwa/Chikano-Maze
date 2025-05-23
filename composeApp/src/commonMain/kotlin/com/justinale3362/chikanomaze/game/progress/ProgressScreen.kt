package com.justinale3362.chikanomaze.game.progress

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import chikanomaze.composeapp.generated.resources.full_star
import chikanomaze.composeapp.generated.resources.progress
import chikanomaze.composeapp.generated.resources.star_full
import com.justinale3362.chikanomaze.data.progressItems
import com.justinale3362.chikanomaze.util.ui.BackButton
import com.justinale3362.chikanomaze.util.ui.GameText
import com.justinale3362.chikanomaze.util.ui.ProgressItemComponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProgressScreen(navController: NavController, viewModel: ProgressViewModel = koinViewModel()) {
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
                    GameText(
                        Modifier.align(Alignment.Center),
                        text = stringResource(Res.string.progress),
                        fontSize = 30.sp,
                        color = Color(0xff7AFCDA)
                    )
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    GameText(
                        text = state.gameSaveData.allStars.toString(),
                        fontSize = 30.sp,
                        color = Color(0xff7AFCDA)
                    )
                    Image(
                        modifier = Modifier.width(40.dp),
                        painter = painterResource(Res.drawable.star_full),
                        contentDescription = stringResource(Res.string.full_star),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
            items(progressItems) {
                ProgressItemComponent(
                    Modifier.height(75.dp).fillMaxWidth().padding(horizontal = 10.dp),
                    progressItem = it,
                    isUnlocked = state.gameSaveData.allStars >= it.starsToUnlock
                )
            }
        }
    }
}
