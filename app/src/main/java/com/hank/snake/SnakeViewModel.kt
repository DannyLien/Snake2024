package com.hank.snake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.fixedRateTimer
import kotlin.io.path.createTempDirectory

class SnakeViewModel : ViewModel() {
    var body = MutableLiveData<List<Position>>()
    var apple = MutableLiveData<Position>()
    var score = MutableLiveData<Int>()
    var snakeBody = mutableListOf<Position>()
    var direction = Direction.LEFT

    fun start() {
        snakeBody.apply {
            add(Position(10, 10))
            add(Position(11, 10))
            add(Position(12, 10))
            add(Position(13, 10))
        }.also {
            body.value = it
        }
        fixedRateTimer("timer", true, 500, 500) {
            val pos = snakeBody.first().copy().apply {
                when (direction) {
                    Direction.LEFT -> x--
                    Direction.RIGHT -> x++
                    Direction.TOP -> y--
                    Direction.DOWN -> y++
                }
            }
            snakeBody.add(0, pos)
            snakeBody.removeLastOrNull()
            body.postValue(snakeBody)
        }
    }

    fun reset() {}

    fun move(dir: Direction) {}


}


data class Position(var x: Int, var y: Int)


enum class Direction { TOP, RIGHT, LEFT, DOWN }






