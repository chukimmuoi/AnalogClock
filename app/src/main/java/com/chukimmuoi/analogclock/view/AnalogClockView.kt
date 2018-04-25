package com.chukimmuoi.analogclock.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.chukimmuoi.analogclock.R
import com.chukimmuoi.analogclock.util.changeToTextSize
import com.chukimmuoi.analogclock.util.convertDpToPixel
import com.chukimmuoi.analogclock.view.model.Center
import com.chukimmuoi.analogclock.view.model.Circle
import com.chukimmuoi.analogclock.view.model.Hands
import com.chukimmuoi.analogclock.view.model.Number

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://cafe365.pos365.vn/
 * @Project : AnalogClock
 * Created by chukimmuoi on 23/04/2018.
 */
class AnalogClockView : View {

    companion object {
        private const val TAG = "AnalogClockView"

        private const val RADIUS_DEFAULT = 144
        private const val STROKE_DEFAULT = 1
    }

    private var mBackgroundColor = 0
    private var mBackgroundRadius = 0
    private var mTextSize = 0F
    private var mNumberType = Number.NUMBER_TYPE

    private var mStrokeColor = 0
    private var mStrokeWidth = 0

    private var mHoursColor = 0
    private var mHoursLength = 0

    private var mMinuteColor = 0
    private var mMinuteLength = 0

    private var mSecondColor = 0
    private var mSeCondLength = 0

    private var mCurrentWidth = 0

    private lateinit var mCircle: Circle
    private lateinit var mCenter: Center
    private lateinit var mNumber: Number
    private lateinit var mHands: Hands

    init {

    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AnalogClockView)

        mBackgroundColor = typeArray.getColor(
                R.styleable.AnalogClockView_analogClockBackgroundColor,
                Color.BLACK
        )
        mBackgroundRadius = typeArray.getDimensionPixelSize(
                R.styleable.AnalogClockView_analogClockRadius,
                RADIUS_DEFAULT.convertDpToPixel(resources)
        )
        mTextSize = Math.min(
                typeArray.getDimension(
                        R.styleable.AnalogClockView_analogClockTextSize,
                        (RADIUS_DEFAULT / 9).changeToTextSize(resources)
                ), (mBackgroundRadius / 9).toFloat())
        mNumberType = typeArray.getInt(
                R.styleable.AnalogClockView_analogClockType,
                Number.NUMBER_TYPE
        )
        mStrokeColor = typeArray.getColor(
                R.styleable.AnalogClockView_analogClockStrokeColor,
                Color.BLACK
        )
        mStrokeWidth = typeArray.getDimensionPixelSize(
                R.styleable.AnalogClockView_analogClockStrokeWidth,
                STROKE_DEFAULT.convertDpToPixel(resources)
        )
        mHoursColor = typeArray.getColor(
                R.styleable.AnalogClockView_analogClockHoursColor,
                Color.WHITE
        )
        mHoursLength = Math.min(
                typeArray.getDimensionPixelSize(
                        R.styleable.AnalogClockView_analogClockHoursLength,
                        (RADIUS_DEFAULT * 0.5).toInt().convertDpToPixel(resources)
                ), mBackgroundRadius)
        mMinuteColor = typeArray.getColor(
                R.styleable.AnalogClockView_analogClockMinuteColor,
                Color.WHITE
        )
        mMinuteLength = Math.min(
                typeArray.getDimensionPixelSize(
                        R.styleable.AnalogClockView_analogClockMinuteLength,
                        (RADIUS_DEFAULT * 0.7).toInt().convertDpToPixel(resources)
                ), mBackgroundRadius)
        mSecondColor = typeArray.getColor(
                R.styleable.AnalogClockView_analogClockSecondColor,
                Color.WHITE
        )
        mSeCondLength = Math.min(
                typeArray.getDimensionPixelSize(
                        R.styleable.AnalogClockView_analogClockSecondLength,
                        (RADIUS_DEFAULT * 0.9).toInt().convertDpToPixel(resources)
                ), mBackgroundRadius)

        typeArray.recycle()

        createUI()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun createUI () {
        mCircle = Circle(mStrokeColor, mStrokeWidth.toFloat(), mBackgroundRadius.toFloat())
        mCenter= Center(mStrokeColor, mStrokeWidth.toFloat(),
                mBackgroundColor, mBackgroundRadius.toFloat())
        mNumber = Number(
                mStrokeColor, mStrokeWidth.toFloat(),
                mTextSize,
                mBackgroundRadius.toFloat(),
                mNumberType)
        mHands = Hands(mStrokeWidth.toFloat(),
                mHoursColor, mHoursLength,
                mMinuteColor, mMinuteLength,
                mSecondColor, mSeCondLength)


        mCurrentWidth = mBackgroundRadius * 2
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = mCurrentWidth
        val desireHeight = mCurrentWidth

        val width  = resolveSize(desiredWidth, widthMeasureSpec)
        val height = resolveSize(desireHeight, heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // Center screen.
        val xCenter = width * 0.5F
        val yCenter = height * 0.5F

        // Circle.
        mCircle.setCoordinates(xCenter, yCenter)
        // Center.
        mCenter.setCoordinates(xCenter, yCenter)
        // Number.
        mNumber.setCoordinates(xCenter, yCenter)
        // Hand.
        mHands.setCoordinates(xCenter, yCenter)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mCircle.draw(canvas)
        mCenter.draw(canvas)
        mNumber.draw(canvas)
        mHands.draw(canvas)

        postInvalidateDelayed(1000)
        invalidate()
    }
}