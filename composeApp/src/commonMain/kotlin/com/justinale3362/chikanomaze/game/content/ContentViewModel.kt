package com.justinale3362.chikanomaze.game.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justinale3362.chikanomaze.data.repository.GameSaveRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContentViewModel(
    private val repository: GameSaveRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ContentState())
    val state = _state.asStateFlow()


    fun save(value: String) = viewModelScope.launch {
        repository.setCache(value)
        _state.update { it.copy(isSaved = true) }
    }

    fun addPopup() = viewModelScope.launch {
        _state.update { it.copy(hasPopup = true) }
    }

    fun closeAllPopups() = viewModelScope.launch {
        _state.update { it.copy(hasPopup = false) }
    }
}