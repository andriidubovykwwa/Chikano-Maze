package com.justinale3362.chikanomaze.game.tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import chikanomaze.composeapp.generated.resources.Res
import chikanomaze.composeapp.generated.resources.acceleration
import chikanomaze.composeapp.generated.resources.bg
import chikanomaze.composeapp.generated.resources.bush
import chikanomaze.composeapp.generated.resources.car
import chikanomaze.composeapp.generated.resources.empty_star
import chikanomaze.composeapp.generated.resources.full_star
import chikanomaze.composeapp.generated.resources.grass
import chikanomaze.composeapp.generated.resources.star_empty
import chikanomaze.composeapp.generated.resources.star_full
import chikanomaze.composeapp.generated.resources.tunnel
import chikanomaze.composeapp.generated.resources.tutorial
import chikanomaze.composeapp.generated.resources.underground_way
import com.justinale3362.chikanomaze.util.ui.BackButton
import com.justinale3362.chikanomaze.util.ui.GameText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TutorialScreen(navController: NavController) {
    val defaultFontSize = 18.sp
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg),
            contentScale = ContentScale.FillBounds
        ).background(Color(0x90000000))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = WindowInsets.safeContent.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
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
                        text = stringResource(Res.string.tutorial),
                        fontSize = 30.sp,
                        color = Color(0xffFFFFFF)
                    )
                }
            }
            item {
                Text(
                    "Welcome to the maze! You control a chicken trying to escape from a tricky labyrinth avoiding cars. Your main goal is to reach the exit (house), but you can also collect up to two eggs hidden in different parts of the level.",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                Text(
                    "Level ratings depend on how many eggs you collect:\n" +
                            "- Escape only: 1 star\n" +
                            "- Escape + 1 egg: 2 stars\n" +
                            "- Escape + 2 eggs: 3 stars",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                val stars = 2
                Row(
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val emptyStars = 3 - stars
                    repeat(stars) {
                        Image(
                            modifier = Modifier.width(40.dp),
                            painter = painterResource(Res.drawable.star_full),
                            contentDescription = stringResource(Res.string.full_star),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    repeat(emptyStars) {
                        Image(
                            modifier = Modifier.width(40.dp),
                            painter = painterResource(Res.drawable.star_empty),
                            contentDescription = stringResource(Res.string.empty_star),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
            item {
                Text(
                    "The game encourages exploration and finding the most efficient route.",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                Text(
                    "Additional gameplay mechanics:",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                Text(
                    "- Underground tunnels: A pair of connected cells lets you instantly travel between them.",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                Image(
                    modifier = Modifier.width(50.dp),
                    painter = painterResource(Res.drawable.tunnel),
                    contentDescription = stringResource(Res.string.underground_way),
                    contentScale = ContentScale.FillWidth
                )
            }
            item {
                Text(
                    "- Bushes: Hiding in bushed makes the chicken invisible to cars.",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                Image(
                    modifier = Modifier.width(50.dp),
                    painter = painterResource(Res.drawable.grass),
                    contentDescription = stringResource(Res.string.bush),
                    contentScale = ContentScale.FillWidth
                )
            }
            item {
                Text(
                    "- Enemy cars: These follow fixed paths (roads). If they get close to the chicken (2 cells radius), you lose.",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                Image(
                    modifier = Modifier.width(50.dp),
                    painter = painterResource(Res.drawable.car),
                    contentDescription = stringResource(Res.string.car),
                    contentScale = ContentScale.FillWidth
                )
            }
            item {
                Text(
                    "- Move limit: Each level has a limited number of moves (displayed at the top of the screen), so plan carefully.",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                GameText(
                    text = "15",
                    fontSize = 30.sp,
                    color = Color(0xffFFFFFF)
                )
            }
            item {
                Text(
                    "- Acceleration: Once per level, you can acceleration to move 4 cells in a single turn. This can help you escape danger.",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
            item {
                Image(
                    modifier = Modifier.width(50.dp),
                    painter = painterResource(Res.drawable.acceleration),
                    contentDescription = stringResource(Res.string.acceleration),
                    contentScale = ContentScale.FillWidth
                )
            }
            item {
                Text(
                    "The more stars you collect, the more rewards you unlock, including new chicken skins and more accelerations per level.",
                    color = Color(0xffFFFFFF),
                    fontSize = defaultFontSize
                )
            }
        }
    }
}
