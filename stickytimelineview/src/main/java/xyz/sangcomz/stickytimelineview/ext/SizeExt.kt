package xyz.sangcomz.stickytimelineview.ext

import android.content.Context
import android.util.TypedValue

/**
 * Created by seokwon.jeong on 16/11/2017.
 */
fun Int.DP(context: Context): Float = (this * context.resources.displayMetrics.density)

/**
 * Todo dpToPx
 */
fun Number.dpToPx(context: Context) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)

fun Float.DP(context: Context): Float = (this * context.resources.displayMetrics.density)