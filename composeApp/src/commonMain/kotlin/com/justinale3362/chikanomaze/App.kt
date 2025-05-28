package com.justinale3362.chikanomaze

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.justinale3362.chikanomaze.game.Game
import com.justinale3362.chikanomaze.util.appModule
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            // TODO: better quality chicken images (figma x download new)
            Game()
        }
    }
}