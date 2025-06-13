package com.hank.snake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

class SnakeViewModel : ViewModel() {
    private lateinit var gameTimer: Timer
    var body = MutableLiveData<List<Position>>()
    var apple = MutableLiveData<Position>()
    var score = MutableLiveData<Int>()
    var gameState = MutableLiveData<GameState>()
    var snakeBody = mutableListOf<Position>()
    var direction = Direction.LEFT
    private lateinit var applePos: Position
    var point: Int = 0

    fun start() {
        score.postValue(point)
        snakeBody.apply {
            add(Position(10, 10))
            add(Position(11, 10))
            add(Position(12, 10))
            add(Position(13, 10))
        }.also {
            body.value = it
        }
        generateApple()
        gameTimer = fixedRateTimer("timer", true, 300, 300) {
            val pos = snakeBody.first().copy().apply {
                when (direction) {
                    Direction.LEFT -> x--
                    Direction.RIGHT -> x++
                    Direction.TOP -> y--
                    Direction.DOWN -> y++
                }
                if (snakeBody.contains(this) || x < 0 || x >= 20 || y < 0 || y >= 20) {
                    cancel()
                    gameState.postValue(GameState.GAME_OVER)
                }
            }
            snakeBody.add(0, pos)
            if (pos != applePos) {
                snakeBody.removeLastOrNull()
            } else {
                point += 100
                score.postValue(point)
                generateApple()
            }
            body.postValue(snakeBody)
        }
    }

    fun generateApple() {
        val spots = mutableListOf<Position>().apply {
            for (i in (0..19)) {
                for (j in (0..19)) {
                    add(Position(i, j))
                }
            }
        }
        spots.removeAll(snakeBody)
        spots.shuffle()
        applePos = spots[0]
        apple.postValue(applePos)
    }

    fun reset() {
        gameTimer.cancel()
        point = 0
        snakeBody.clear()
        direction = Direction.LEFT
    }

    fun move(dir: Direction) {
        if (dir == Direction.TOP && direction == Direction.DOWN ||
            dir == Direction.DOWN && direction == Direction.TOP ||
            dir == Direction.LEFT && direction == Direction.RIGHT ||
            dir == Direction.RIGHT && direction == Direction.LEFT
        ) {
            return
        } else {
            direction = dir
        }
    }

}

data class Position(var x: Int, var y: Int)

enum class Direction { TOP, RIGHT, LEFT, DOWN }

enum class GameState { ONGOING, GAME_OVER }


