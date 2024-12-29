package com.hank.snake

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var snakeBody: List<Position>? = null
    var size = 0
    val gap = 3
    val paint = Paint().apply {
        color = Color.BLACK
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.run {
            snakeBody?.forEach {
                drawRect(
                    (it.x * size).toFloat() + gap,
                    (it.y * size).toFloat() + gap,
                    ((it.x + 1) * size).toFloat() - gap,
                    ((it.y + 1) * size).toFloat() - gap, paint
                )
            }
        }
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        size = width / 20
    }


}