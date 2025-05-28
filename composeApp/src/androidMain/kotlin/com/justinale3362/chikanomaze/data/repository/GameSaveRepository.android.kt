package com.justinale3362.chikanomaze.data.repository

class AndroidGameSaveRepository : GameSaveRepository {
    override fun getGameSave(): GameSaveData {
        // Android implementation
        return GameSaveData()
    }

    override fun setGameSave(gameSaveData: GameSaveData) {
        // Android implementation
    }

    override fun setCache(cache: String) {
        // Android implementation
    }

    override fun getCache(): String? {
        // Android implementation
        return null
    }

}

actual fun getGameSaveRepository(): GameSaveRepository = AndroidGameSaveRepository()