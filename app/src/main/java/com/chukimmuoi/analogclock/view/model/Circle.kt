package com.chukimmuoi.analogclock.view.model

import android.graphics.Canvas
import android.graphics.Paint

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://cafe365.pos365.vn/
 * @Project : AnalogClock
 * Created by chukimmuoi on 24/04/2018.
 */
class Circle(private val mStrokeColor: Int, private val mStrokeWidth: Float, private val mRadius: Float) {

    private val mPaint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    private var mXCenter = 0F
    private var mYCenter = 0F

    init {
        with(mPaint) {
            isAntiAlias = true
            style = Paint.Style.STROKE
            color = mStrokeColor
            strokeWidth = mStrokeWidth
        }
    }

    fun setCoordinates(xCenter: Float, yCenter: Float) : Circle {
        mXCenter = xCenter
        mYCenter = yCenter

        return this@Circle
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(mXCenter, mYCenter, mRadius, mPaint)
    }
}