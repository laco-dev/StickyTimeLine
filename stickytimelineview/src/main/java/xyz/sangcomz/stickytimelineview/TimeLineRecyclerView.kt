package xyz.sangcomz.stickytimelineview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import xyz.sangcomz.stickytimelineview.model.RecyclerViewAttr


/*
 * Copyright 2018 SeokWon Jeong.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class TimeLineRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    private var recyclerViewAttr: RecyclerViewAttr? = null

    /**
     * Todo lateinit var
     */
    // private lateinit var recyclerViewAttr: RecyclerViewAttr
    init {
        attrs?.let {
            /**
             * Todo context.obtainStyleAttributes >> 그리고 NonNull이다.
             */
            // context.obtainStyledAttributes(...)
            val a = context.theme?.obtainStyledAttributes(
                    attrs,
                    R.styleable.TimeLineRecyclerView,
                    0, 0)

            a?.let {
                recyclerViewAttr =
                        RecyclerViewAttr(
                                getColor(it, R.styleable.TimeLineRecyclerView_sectionBackgroundColor, R.color.colorDefaultBackground),
                                getColor(it, R.styleable.TimeLineRecyclerView_sectionTitleTextColor, R.color.colorDefaultTitle),
                                it.getColor(R.styleable.TimeLineRecyclerView_sectionSubTitleTextColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultSubTitle)),
                                it.getColor(R.styleable.TimeLineRecyclerView_timeLineColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultTitle)),
                                it.getColor(R.styleable.TimeLineRecyclerView_timeLineCircleColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultTitle)),
                                it.getColor(R.styleable.TimeLineRecyclerView_timeLineCircleStrokeColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultStroke)),
                                getDimension(it, R.styleable.TimeLineRecyclerView_sectionTitleTextSize, R.dimen.title_text_size),
                                it.getDimension(R.styleable.TimeLineRecyclerView_sectionSubTitleTextSize,
                                        context.resources.getDimension(R.dimen.sub_title_text_size)),
                                it.getDimension(R.styleable.TimeLineRecyclerView_timeLineWidth,
                                        context.resources.getDimension(R.dimen.line_width)),
                                it.getBoolean(R.styleable.TimeLineRecyclerView_isSticky, true),
                                it.getDrawable(R.styleable.TimeLineRecyclerView_customDotDrawable))
            }

            // Todo: Null 처리 이후
            with(a!!) {
                recyclerViewAttr =
                        RecyclerViewAttr(
                                color(R.styleable.TimeLineRecyclerView_sectionBackgroundColor, R.color.colorDefaultBackground),
                                color(R.styleable.TimeLineRecyclerView_sectionTitleTextColor, R.color.colorDefaultTitle),
                                // ...
                                dimension(R.styleable.TimeLineRecyclerView_sectionSubTitleTextSize, R.dimen.sub_title_text_size),
                                // ...
            }
        }
    }

    /**
     * Add RecyclerSectionItemDecoration for Sticky TimeLineView
     *
     * @param callback SectionCallback
     */
    fun addItemDecoration(callback: RecyclerSectionItemDecoration.SectionCallback) {
        recyclerViewAttr?.let {
            super.addItemDecoration(RecyclerSectionItemDecoration(context, callback, it))
        }
    }

    /**
     * Todo Deprecated
     */
    @Deprecated("Hello World", ReplaceWith("addItemDecoration(callback)", "xyz.sangcomz.stickytimelineview"))
    override fun addItemDecoration(decor: ItemDecoration) {
        super.addItemDecoration(decor)
    }

    @Deprecated("Hello World", ReplaceWith("addItemDecoration(callback)", "xyz.sangcomz.stickytimelineview"))
    override fun addItemDecoration(decor: ItemDecoration, index: Int) {
        super.addItemDecoration(decor, index)
    }

    /**
     * Todo: TypedArray
     */
    private fun TypedArray.color(id: Int, defId: Int) = getColor(id, ContextCompat.getColor(context, defId))

    private fun TypedArray.dimension(id: Int, defId: Int): Float = getDimension(id, context.resources.getDimension(defId))
}

@ColorInt
fun ViewGroup.getColor(ta: TypedArray, id: Int, @ColorRes defId: Int): Int = ta.getColor(id, ContextCompat.getColor(context, defId))

fun ViewGroup.getDimension(ta: TypedArray, id: Int, defId: Int): Float = ta.getDimension(id, context.resources.getDimension(defId))
