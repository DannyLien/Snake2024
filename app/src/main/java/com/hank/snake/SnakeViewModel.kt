package com.hank.snake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SnakeViewModel : ViewModel() {
    var body = MutableLiveData<List<Position>>()
    var apple = MutableLiveData<Position>()
    var score = MutableLiveData<Int>()
    var snakeBody = mutableListOf<Position>()

    fun start() {
        snakeBody.apply {
            add(Position(10, 10))
            add(Position(11, 10))
            add(Position(12, 10))
            add(Position(13, 10))
        }.also {
            body.value = it
        }
    }

    fun reset() {}

    fun move(dir: Direction) {}


}


data class Position(var x: Int, var y: Int)


enum class Direction { TOP, RIGHT, LEFT, DOWN }






