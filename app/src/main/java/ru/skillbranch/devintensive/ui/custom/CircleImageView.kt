package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import ru.skillbranch.devintensive.R
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Paint


class CircleImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): ImageView(context,attrs,defStyleAttr) {
    companion object{
        private const val DEFAULT_BORDER_WIDTH = 2
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
    }

    private var borderWidth = DEFAULT_BORDER_WIDTH
    private var borderColor = DEFAULT_BORDER_COLOR

    private val mDrawableRect = RectF()
    private val mBitmapPaint = Paint()

    init{
        /* Считывание атрибутов */
        if(attrs != null){
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)

            borderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH)
            borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)

            a.recycle()
        }
    }

    fun getBorderWidth():Int{
        return borderWidth
    }

    fun setBorderWidth(dp:Int){
        borderWidth = dp
    }

    fun getBorderColor(): Int {
        return borderColor
    }

    // TODO fix setting value
    fun setBorderColor(hex:String){
        setBorderColor(hex)
    }

    fun setBorderColor(@ColorRes colorId: Int){
        borderColor = colorId
    }
}