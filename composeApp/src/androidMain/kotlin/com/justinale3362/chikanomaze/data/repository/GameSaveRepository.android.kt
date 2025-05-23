package com.justinale3362.chikanomaze.data.repository

class AndroidGameSaveRepository : GameSaveRepository {
    override fun getGameSave(): GameSaveData {
        // TODO: Android implementation
        return GameSaveData()
    }

    override fun setGameSave(gameSaveData: GameSaveData) {
        // TODO: Android implementation
    }

}

actual fun getGameSaveRepository(): GameSaveRepository = AndroidGameSaveRepository()