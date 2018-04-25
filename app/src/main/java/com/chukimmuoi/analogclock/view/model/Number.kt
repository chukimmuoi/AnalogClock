package com.chukimmuoi.analogclock.view.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface


/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://cafe365.pos365.vn/
 * @Project : AnalogClock
 * Created by chukimmuoi on 24/04/2018.
 */
class Number(private val mStrokeColor: Int, private val mStrokeWidth: Float,
             private val mTextSize: Float,
             private val mRadius: Float,
             private val type: Int) {
    companion object {
        const val NUMBER_TYPE  = 0x000a
        const val ETRURIA_TYPE = 0x000b
        const val NONE_TYPE    = 0x000c

        private val numbers =
                listOf(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        private val etruria =
                listOf("XII", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI")
    }

    private val mPaint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    private val mNumberDraws by lazy { mutableListOf<NumberDraw>() }

    private val mRect by lazy { Rect() }

    private var mSpaceCenterToNumber = 0F

    init {
        with(mPaint) {
            isAntiAlias = true
            style = Paint.Style.STROKE
            color = mStrokeColor
            textSize = mTextSize
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
        }
        mSpaceCenterToNumber = mRadius - mTextSize / 2 - mStrokeWidth
    }

    fun setCoordinates(xCenter: Float, yCenter: Float) : Number {
        mNumberDraws.clear()
        for (i in 0 until 12) {
            val text = when (type) {
                NUMBER_TYPE -> {
                    numbers[i].toString()
                }
                ETRURIA_TYPE -> {
                    etruria[i]
                }
                else -> {
                    numbers[i].toString()
                }
            }
            mPaint.getTextBounds(text, 0, text.length, mRect) // Trả về hình chữ nhật bao quanh text, ở đây là mRect. Dựa vào đó biết đươc width và heigth của text.
            val angle = Math.PI / 6 * (i - 3) // 6 - 3, 30 - 15
            val x = (xCenter + Math.cos(angle) * mSpaceCenterToNumber - mRect.width() / 2).toFloat() // - mRect.width() / 2 mục đích để toạ độ x ở chính giữa text cần vẽ.
            val y = (yCenter + Math.sin(angle) * mSpaceCenterToNumber + mRect.height() / 2).toFloat() // + mRect.height() / 2 mục đích để toạ độ y ở chính giữa text cần vẽ.
            mNumberDraws.add(i, NumberDraw(text, x, y))
        }

        return this@Number
    }

    fun draw(canvas: Canvas) {
        for (i in 0 until 12) {
            val numberDraw = mNumberDraws[i]
            canvas.drawText(numberDraw.text, numberDraw.x, numberDraw.y, mPaint)
        }
    }
}

data class NumberDraw(val text: String = "", val x: Float = 0F, val y: Float = 0F)