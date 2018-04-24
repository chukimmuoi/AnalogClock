package com.chukimmuoi.analogclock.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.chukimmuoi.analogclock.R
import com.chukimmuoi.analogclock.util.convertDpToPixel
import com.chukimmuoi.analogclock.view.model.Center
import com.chukimmuoi.analogclock.view.model.Circle

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

    private var mBackgroundColor: Int = 0
    private var mBackgroundRadius: Int = 0

    private var mStrokeColor: Int = 0
    private var mStrokeWidth: Int = 0

    private var mHoursColor: Int = 0
    private var mHoursLength: Int = 0

    private var mMinuteColor: Int = 0
    private var mMinuteLength: Int = 0

    private var mSecondColor: Int = 0
    private var mSeCondLength: Int = 0

    private var mCurrentWidth: Int = 0

    private lateinit var mCircle: Circle
    private lateinit var mCenter: Center

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
        mHoursLength = typeArray.getDimensionPixelSize(
                R.styleable.AnalogClockView_analogClockHoursLength,
                (RADIUS_DEFAULT * 0.5).toInt().convertDpToPixel(resources)
        )
        mMinuteColor = typeArray.getColor(
                R.styleable.AnalogClockView_analogClockMinuteColor,
                Color.WHITE
        )
        mMinuteLength = typeArray.getDimensionPixelSize(
                R.styleable.AnalogClockView_analogClockMinuteLength,
                (RADIUS_DEFAULT * 0.7).toInt().convertDpToPixel(resources)
        )
        mSecondColor = typeArray.getColor(
                R.styleable.AnalogClockView_analogClockSecondColor,
                Color.WHITE
        )
        mSeCondLength = typeArray.getDimensionPixelSize(
                R.styleable.AnalogClockView_analogClockSecondLength,
                (RADIUS_DEFAULT * 0.9).toInt().convertDpToPixel(resources)
        )

        typeArray.recycle()

        createUI()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun createUI () {
        mCircle = Circle(mStrokeColor, mStrokeWidth.toFloat(), mBackgroundRadius.toFloat())
        mCenter= Center(mStrokeColor, mStrokeWidth.toFloat(),
                mBackgroundColor, mBackgroundRadius.toFloat())

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
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mCircle.draw(canvas)
        mCenter.draw(canvas)
    }
}