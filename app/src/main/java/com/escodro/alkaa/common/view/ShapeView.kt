package com.escodro.alkaa.common.view

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Custom view to load and change the color od a given shape.
 */
class ShapeView : AppCompatImageView {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    /**
     * Sets the shape color.
     *
     * @param hexCode hex color in String format
     */
    fun setShapeColor(hexCode: String) {
        val drawable = drawable.mutate()
        drawable.colorFilter =
                PorterDuffColorFilter(Color.parseColor(hexCode), PorterDuff.Mode.MULTIPLY)
    }
}
