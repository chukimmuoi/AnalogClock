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

/**
 * @param count có giá trị là 12 (tương đương với 12 vị trí giờ) hoặc 60 (tương đương 60 vị trí phút)
 * */
fun Float.angle(count: Int) : Double {
    return  Math.PI / (count / 2) * (this - count / 4)
}

/**
 * 60 vị trí cách đều nhau trên hình tròn.
 * */
fun Float.angle60() : Double {
    return angle(60)
}

/**
 * 12 vị trí cách đều nhau trên hình tròn.
 * */
fun Float.angle12() : Double {
    return angle(12)
}

/**
 * 4 vị trí cách đều nhau trên hình tròn.
 * */
fun Float.angle4() : Double {
    return angle(4)
}

/**
 * @param count có giá trị là 12 (tương đương với 12 vị trí giờ) hoặc 60 (tương đương 60 vị trí phút)
 * */
fun Int.angle(count: Int) : Double {
    return  Math.PI / (count / 2) * (this - count / 4)
}

/**
 * 60 vị trí cách đều nhau trên hình tròn.
 * */
fun Int.angle60() : Double {
    return angle(60)
}

/**
 * 12 vị trí cách đều nhau trên hình tròn.
 * */
fun Int.angle12() : Double {
    return angle(12)
}

/**
 * 4 vị trí cách đều nhau trên hình tròn.
 * */
fun Int.angle4() : Double {
    return angle(4)
}