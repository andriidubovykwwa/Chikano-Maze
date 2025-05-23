package com.justinale3362.chikanomaze.game.level_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justinale3362.chikanomaze.data.repository.GameSaveRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LevelSelectionViewModel(
    private val repository: GameSaveRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LevelSelectionState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(gameSaveData = repository.getGameSave()) }
        }
    }
}