package com.justinale3362.chikanomaze.data.repository

import android.content.Context
import androidx.core.content.edit
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin

class AndroidGameSaveRepository : GameSaveRepository {
    private val context: Context = getKoin().get()
    private val sharedPreferences = context.getSharedPreferences("game_save", Context.MODE_PRIVATE)

    override fun getGameSave(): GameSaveData {
        return if (sharedPreferences.contains(GAME_DATA_KEY)) {
            try {
                val jsonStr = sharedPreferences.getString(GAME_DATA_KEY, "").toString()
                Json.decodeFromString<GameSaveData>(jsonStr)
            } catch (_: Exception) {
                GameSaveData()
            }
        } else {
            GameSaveData()
        }
    }

    override fun setGameSave(gameSaveData: GameSaveData) {
        try {
            val jsonStr = Json.encodeToString(gameSaveData)
            sharedPreferences.edit { putString(GAME_DATA_KEY, jsonStr) }
        } catch (_: Exception) {
        }
    }

    companion object {
        private const val GAME_DATA_KEY = "game_data"
    }

}

actual fun getGameSaveRepository(): GameSaveRepository = AndroidGameSaveRepository()