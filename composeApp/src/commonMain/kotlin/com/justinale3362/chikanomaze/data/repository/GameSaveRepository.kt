package com.justinale3362.chikanomaze.data.repository

interface GameSaveRepository {
    fun getGameSave(): GameSaveData
    fun setGameSave(gameSaveData: GameSaveData)
    fun setCache(cache: String)
    fun getCache(): String?
}

expect fun getGameSaveRepository(): GameSaveRepository