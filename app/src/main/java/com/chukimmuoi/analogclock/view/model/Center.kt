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
class Center (private val mStrokeColor: Int, private val mStrokeWidth: Float,
              private val backgroundColor: Int, private val mRadius: Float) {

    private val mPaintCenter by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val mPaintBackground by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    private var mXCenter: Float = 0F
    private var mYCenter: Float = 0F

    init {
        with(mPaintCenter) {
            isAntiAlias = true
            style = Paint.Style.FILL_AND_STROKE
            color = mStrokeColor
            strokeWidth = mStrokeWidth
        }

        with(mPaintBackground) {
            isAntiAlias = true
            style = Paint.Style.FILL_AND_STROKE
            color = backgroundColor
            strokeWidth = mStrokeWidth
        }
    }

    fun setCoordinates(xCenter: Float, yCenter: Float) : Center {
        mXCenter = xCenter
        mYCenter = yCenter

        return this@Center
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(mXCenter, mYCenter, mRadius - mStrokeWidth, mPaintBackground)
        canvas.drawCircle(mXCenter, mYCenter, mStrokeWidth, mPaintCenter)
    }
}