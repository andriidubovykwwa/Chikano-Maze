package com.justinale3362.chikanomaze.data.repository

import kotlinx.serialization.json.Json
import platform.Foundation.NSUserDefaults

class IOSGameSaveRepository : GameSaveRepository {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    override fun getGameSave(): GameSaveData {
        return if (userDefaults.objectForKey(GAME_SAVE_KEY) != null) {
            try {
                val jsonStr = userDefaults.stringForKey(GAME_SAVE_KEY) ?: ""
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
            userDefaults.setObject(jsonStr, forKey = GAME_SAVE_KEY)
        } catch (_: Exception) {
        }
    }

    companion object {
        private const val GAME_SAVE_KEY = "game_save"
    }

}

actual fun getGameSaveRepository(): GameSaveRepository = IOSGameSaveRepository()