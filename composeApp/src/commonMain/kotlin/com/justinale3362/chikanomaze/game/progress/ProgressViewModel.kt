package com.justinale3362.chikanomaze.game.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justinale3362.chikanomaze.data.repository.GameSaveRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProgressViewModel(
    private val repository: GameSaveRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProgressState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(gameSaveData = repository.getGameSave()) }
        }
    }
}