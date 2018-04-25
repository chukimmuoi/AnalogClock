package com.chukimmuoi.analogclock.util

import android.content.res.Resources

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://cafe365.pos365.vn/
 * @Project : AnalogClock
 * Created by chukimmuoi on 23/04/2018.
 */
// scaleDensity: Dung cho font chu
// density: Dung cho size
fun Float.convertPixelToDp(resources: Resources) : Float {
    return this / resources.displayMetrics.density
}

fun Float.convertDpToPixel(resources: Resources) : Float {
    return this * resources.displayMetrics.density
}

fun Int.convertPixelToDp(resources: Resources) : Int {
    return Math.round(this / resources.displayMetrics.density)
}

fun Int.convertDpToPixel(resources: Resources) : Int {
    return Math.round(this * resources.displayMetrics.density)
}

fun Float.changeToTextSize(resources: Resources) : Float {
    return Math.round(this * resources.displayMetrics.scaledDensity).toFloat()
}

fun Int.changeToTextSize(resources: Resources) : Float {
    return Math.round(this * resources.displayMetrics.scaledDensity).toFloat()
}