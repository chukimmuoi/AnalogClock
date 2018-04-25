package com.chukimmuoi.analogclock.view.model

import android.graphics.Canvas
import android.graphics.Paint
import java.util.*

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://cafe365.pos365.vn/
 * @Project : AnalogClock
 * Created by chukimmuoi on 24/04/2018.
 */
class Hands (private val mStrokeWidth: Float,
             private val mHoursColor: Int, private val mHoursLength: Int,
             private val mMinuteColor: Int, private val mMinuteLength: Int,
             private val mSecondColor: Int, private val mSecondLength: Int) {

    private val mPaintHours by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val mPaintMinus by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val mPaintSecond by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    private var mX = 0F
    private var mY = 0F

    init {
        with(mPaintHours) {
            isAntiAlias = true
            color = mHoursColor
            strokeWidth = mStrokeWidth * 0.9F
            strokeCap = Paint.Cap.ROUND
        }
        with(mPaintMinus) {
            isAntiAlias = true
            color = mMinuteColor
            strokeWidth = mStrokeWidth * 0.7F
            strokeCap = Paint.Cap.ROUND
        }
        with(mPaintSecond) {
            isAntiAlias = true
            color = mSecondColor
            strokeWidth = mStrokeWidth * 0.5F
            strokeCap = Paint.Cap.ROUND
        }
    }

    fun setCoordinates(xCenter: Float, yCenter: Float) : Hands {
        mX = xCenter
        mY = yCenter
        return this@Hands
    }

    private fun drawLine(canvas: Canvas, angle: Double, length: Int, paint: Paint) {
        canvas.drawLine(mX, mY,
                (mX + Math.cos(angle) * length).toFloat(),
                (mY + Math.sin(angle) * length).toFloat(),
                paint)
    }

    fun draw(canvas: Canvas) {
        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        if (hours > 12) hours - 12

        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        val minuteCurrent = minute + second / 60F
        val hoursCurrent = hours + minuteCurrent / 60F

        val angleHouse = Math.PI / 30 * ((hoursCurrent * 5F) - 15)
        drawLine(canvas, angleHouse, mHoursLength, mPaintHours)

        val angleMinute = Math.PI / 30 * (minuteCurrent - 15)
        drawLine(canvas, angleMinute, mMinuteLength, mPaintMinus)

        val angleSecond = Math.PI / 30 * (second - 15)
        drawLine(canvas, angleSecond, mSecondLength, mPaintSecond)
    }
}