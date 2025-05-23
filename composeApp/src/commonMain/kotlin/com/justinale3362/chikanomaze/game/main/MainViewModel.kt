package com.justinale3362.chikanomaze.game.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.justinale3362.chikanomaze.data.CarMovement.Companion.DEFAULT_FACE_DIRECTION
import com.justinale3362.chikanomaze.data.Cell
import com.justinale3362.chikanomaze.data.Direction
import com.justinale3362.chikanomaze.data.LevelGenerator
import com.justinale3362.chikanomaze.data.Path
import com.justinale3362.chikanomaze.data.progressItems
import com.justinale3362.chikanomaze.data.repository.GameSaveRepository
import com.justinale3362.chikanomaze.game.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: GameSaveRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val lvl = savedStateHandle.toRoute<Screen.Main>().lvl
            initLvl(lvl)
        }
    }


    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.MoveChicken -> onMoveChicken(event.direction)
            is MainEvent.SkipTurn -> onSkipTurn()
            is MainEvent.UseAcceleration -> onUseAcceleration()
            is MainEvent.Continue -> onContinue()
            is MainEvent.RegisterCompletedLvl -> onRegisterCompletedLvl()
        }
    }

    private fun onRegisterCompletedLvl() = viewModelScope.launch {
        val gameSave = repository.getGameSave()
        val stars = gameSave.stars.toMutableList()
        if (state.value.stars > stars[state.value.lvl - 1]) {
            stars[state.value.lvl - 1] = state.value.stars
        }
        val newGameSave = gameSave.copy(stars = stars)
        repository.setGameSave(newGameSave)
    }

    private fun onContinue() = viewModelScope.launch {
        if (state.value.isLevelCompleted) initLvl(state.value.lvl + 1)
        else initLvl(state.value.lvl)
    }

    private fun onMoveChicken(direction: Direction) = viewModelScope.launch {
        if (state.value.isLevelEnded) return@launch
        if (!state.value.canChickenMove(direction)) return@launch
        val chickenPos = state.value.chickenPos
        val targetPos = state.value.getNeighbor(state.value.chickenPos, direction) ?: return@launch
        val newChickenFaceDirection = when (targetPos) {
            chickenPos + 1 -> {
                Direction.RIGHT
            }

            chickenPos - 1 -> {
                Direction.LEFT
            }

            chickenPos + 10 -> {
                Direction.DOWN
            }

            chickenPos - 10 -> {
                Direction.UP
            }

            else -> DEFAULT_FACE_DIRECTION
        }
        val targetCell = state.value.gameField[targetPos]
        val newChickenPos = if (targetCell == Cell.TELEPORT) {
            state.value.gameField
                .withIndex()
                .first { it.value == Cell.TELEPORT && it.index != targetPos }
                .index
        } else targetPos
        val newEggs = if (targetCell == Cell.EGG) {
            state.value.eggs + 1
        } else state.value.eggs
        val newTargetCell = if (targetCell == Cell.EGG) {
            Cell.EMPTY
        } else targetCell
        val newField = state.value.gameField.mapIndexed { i, cell ->
            if (i == targetPos) newTargetCell else cell
        }
        val newAccelerationMoves = (state.value.accelerationMovesLeft - 1).coerceAtLeast(0)
        _state.update {
            it.copy(
                chickenPos = newChickenPos,
                eggs = newEggs,
                gameField = newField,
                accelerationMovesLeft = newAccelerationMoves,
                movesLeft = if (newAccelerationMoves == 0) (it.movesLeft - 1).coerceAtLeast(0) else it.movesLeft,
                chickenFaceDirection = newChickenFaceDirection
            )
        }
        if (newAccelerationMoves == 0) moveDogs()
    }

    private fun onSkipTurn() = viewModelScope.launch {
        if (state.value.isLevelEnded) return@launch
        val newAccelerationMoves = (state.value.accelerationMovesLeft - 1).coerceAtLeast(0)
        _state.update {
            it.copy(
                movesLeft = if (newAccelerationMoves == 0) (it.movesLeft - 1).coerceAtLeast(0) else it.movesLeft
            )
        }
        moveDogs()
    }

    private fun onUseAcceleration() = viewModelScope.launch {
        if (state.value.isLevelEnded) return@launch
        if (state.value.accelerations <= 0) return@launch
        if (state.value.accelerationMovesLeft > 0) return@launch
        _state.update {
            it.copy(
                accelerations = (it.accelerations - 1).coerceAtLeast(0),
                accelerationMovesLeft = MainState.ACCELERATION_MOVES_DEFAULT
            )
        }
    }

    private fun moveDogs() {
        if (state.value.isLevelEnded) return
        val carMovements = state.value.carMovements
        val newCarMovements = carMovements.map {
            if (it.path == Path.DIRECT) {
                val newPosIndex = it.posIndex + 1
                if (newPosIndex == it.length - 1) {
                    it.copy(posIndex = newPosIndex, path = Path.REVERSE)
                } else it.copy(posIndex = newPosIndex)
            } else {
                val newPosIndex = it.posIndex - 1
                if (newPosIndex == 0) {
                    it.copy(posIndex = newPosIndex, path = Path.DIRECT)
                } else it.copy(posIndex = newPosIndex)
            }
        }
        val gameField =
            state.value.gameField.map { if (it == Cell.CAR) Cell.EMPTY else it }.toMutableList()
        newCarMovements.forEach {
            gameField[it.currentPos] = Cell.CAR
        }
        _state.update { it.copy(carMovements = newCarMovements, gameField = gameField) }
    }

    private fun initLvl(lvl: Int) {
        val gameField = LevelGenerator.generateLevel(lvl).toMutableList()
        val chickenPos = LevelGenerator.chickenPositions[lvl - 1]
        val carMovements = LevelGenerator.dogMovements[lvl - 1]
        val movesLimit = LevelGenerator.movesLimit[lvl - 1]
        carMovements.forEach {
            gameField[it.currentPos] = Cell.CAR
        }
        val gameSave = repository.getGameSave()
        var accelerations = MainState.DEFAULT_ACCELERATIONS
        if (gameSave.allStars >= progressItems[1].starsToUnlock) accelerations += 1
        if (gameSave.allStars >= progressItems[3].starsToUnlock) accelerations += 1
        _state.update {
            MainState(
                lvl = lvl,
                gameField = gameField,
                chickenPos = chickenPos,
                carMovements = carMovements,
                eggs = 0,
                movesLeft = movesLimit,
                fieldWidth = 10,
                accelerations = accelerations,
                skinIndex = gameSave.selectedSkinIndex
            )
        }
    }
}