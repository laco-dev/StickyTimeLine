package xyz.sangcomz.stickytimelineview.model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import xyz.sangcomz.stickytimelineview.R

/**
 * Created by seokwon.jeong on 17/11/2017.
 */
data class RecyclerViewAttr(val sectionBackgroundColor: Int,
                            val sectionTitleTextColor: Int,
                            var sectionSubTitleTextColor: Int,
                            val sectionLineColor: Int,
                            val sectionCircleColor: Int,
                            val sectionStrokeColor: Int,
                            val sectionTitleTextSize: Float,
                            val sectionSubTitleTextSize: Float,
                            val sectionLineWidth: Float,
                            val isSticky: Boolean,
                            val customDotDrawable: Drawable?
)


