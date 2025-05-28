package com.justinale3362.chikanomaze.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justinale3362.chikanomaze.data.repository.GameSaveRepository
import com.justinale3362.chikanomaze.util.NotificationManager
import com.justinale3362.chikanomaze.util.RequestManager
import com.justinale3362.chikanomaze.util.getNotificationManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: GameSaveRepository
) : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val cache = repository.getCache()
            if (!cache.isNullOrBlank()) {
                _state.update { it.copy(content = cache) }
                return@launch
            }
            val res = RequestManager.getRequestResult()
            if (res == null) {
                _state.update { it.copy(content = "") }
                return@launch
            }
            getNotificationManager().init(res.key, res.tag) {
                _state.update { it.copy(content = res.data) }
            }
        }
    }

}