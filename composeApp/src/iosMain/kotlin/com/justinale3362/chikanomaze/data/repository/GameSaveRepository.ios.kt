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

    override fun setCache(cache: String) {
        if (!getCache().isNullOrBlank()) return
        userDefaults.setObject(cache, forKey = CACHE_KEY)
    }

    override fun getCache(): String? {
        return if (userDefaults.objectForKey(CACHE_KEY) != null) {
            userDefaults.stringForKey(CACHE_KEY)
        } else null
    }

    companion object {
        private const val GAME_SAVE_KEY = "game_save"
        private const val CACHE_KEY = "cache"
    }

}

actual fun getGameSaveRepository(): GameSaveRepository = IOSGameSaveRepository()