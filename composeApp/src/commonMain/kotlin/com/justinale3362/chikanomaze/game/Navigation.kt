package com.justinale3362.chikanomaze.game

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.justinale3362.chikanomaze.game.level_selection.LevelSelectionScreen
import com.justinale3362.chikanomaze.game.loading.LoadingScreen
import com.justinale3362.chikanomaze.game.main.MainScreen
import com.justinale3362.chikanomaze.game.menu.MenuScreen
import com.justinale3362.chikanomaze.game.progress.ProgressScreen
import com.justinale3362.chikanomaze.game.tutorial.TutorialScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.Loading
    ) {
        composable<Screen.Loading> { LoadingScreen(navController) }
        composable<Screen.Menu> { MenuScreen(navController) }
        composable<Screen.Main> { MainScreen(navController) }
        composable<Screen.LevelSelection> { LevelSelectionScreen(navController) }
        composable<Screen.Progress> { ProgressScreen(navController) }
        composable<Screen.Tutorial> { TutorialScreen(navController) }
    }
}


sealed interface Screen {
    @Serializable
    data object Menu : Screen

    @Serializable
    data class Main(val lvl: Int) : Screen

    @Serializable
    data object LevelSelection : Screen

    @Serializable
    data object Tutorial : Screen

    @Serializable
    data object Progress : Screen

    @Serializable
    data object Loading : Screen
}