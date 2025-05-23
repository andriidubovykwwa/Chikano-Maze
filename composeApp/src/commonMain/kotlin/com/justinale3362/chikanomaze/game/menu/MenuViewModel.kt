package com.justinale3362.chikanomaze.game.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justinale3362.chikanomaze.data.Direction
import com.justinale3362.chikanomaze.data.repository.GameSaveRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuViewModel(
    private val repository: GameSaveRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MenuState())
    val state = _state.asStateFlow()

    fun onEvent(event: MenuEvent) {
        when (event) {
            is MenuEvent.SwipeSkin -> processSwipeSkin(event.direction)
            is MenuEvent.InitSave -> processInitSave()
        }
    }

    private fun processInitSave() = viewModelScope.launch {
        _state.update { it.copy(gameSaveData = repository.getGameSave()) }
    }

    private fun processSwipeSkin(direction: Direction) = viewModelScope.launch {
        val selectedSkinIndex = state.value.gameSaveData.selectedSkinIndex
        val availableSkins = state.value.gameSaveData.availableSkins
        val newIndex = if (direction == Direction.LEFT) {
            if (selectedSkinIndex > 1) (selectedSkinIndex - 1) else availableSkins - 1
        } else if (direction == Direction.RIGHT) {
            (selectedSkinIndex + 1) % availableSkins
        } else {
            selectedSkinIndex
        }
        val newGameSave = state.value.gameSaveData.copy(selectedSkinIndex = newIndex)
        repository.setGameSave(newGameSave)
        _state.update {
            it.copy(gameSaveData = newGameSave)
        }
    }
}