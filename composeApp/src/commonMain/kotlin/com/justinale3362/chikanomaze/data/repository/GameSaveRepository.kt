package com.justinale3362.chikanomaze.data.repository

interface GameSaveRepository {
    fun getGameSave(): GameSaveData
    fun setGameSave(gameSaveData: GameSaveData)
}

expect fun getGameSaveRepository(): GameSaveRepository