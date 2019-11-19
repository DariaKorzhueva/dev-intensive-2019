package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.dpToPx

class AvatarImageViewShader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_SIZE = 40
        private const val DEFAULT_BODER_WIDTH = 2
        private const val DEFAULT_BODER_COLOR = Color.WHITE
    }

    @Px
    var borderWidth: Float = context.dpToPx(DEFAULT_BODER_WIDTH)
    @ColorInt
    private var borderColor: Int = Color.WHITE
    private var initials: String = "??"

    private val avatarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewRect = Rect()

    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageViewMask)
            borderWidth = ta.getDimension(
                R.styleable.AvatarImageViewShader_aivs_borderWidth,
                context.dpToPx(DEFAULT_BODER_WIDTH)
            )

            borderColor =
                ta.getColor(R.styleable.AvatarImageViewShader_aivs_borderColor, DEFAULT_BODER_COLOR)
            initials = ta.getString(R.styleable.AvatarImageViewShader_aivs_initials) ?: "??"

            ta.recycle()
        }
        scaleType = ScaleType.CENTER_CROP
        setup()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("AvatarImageViewShader", "onMeasure")

        val initSize = resolveDefaultSize(widthMeasureSpec)
        setMeasuredDimension(initSize, initSize)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.e("AvatarImageViewShader", "onSizeChanged")
        if (w == 0) return
        with(viewRect) {
            left = 0
            top = 0
            right = w
            bottom = h
        }

        prepareShader(w, h)
    }

    override fun onDraw(canvas: Canvas?) {
        //super.onDraw(canvas)
        Log.e("AvatarImageViewShader", "onDraw")

        canvas?.drawOval(viewRect.toRectF(),avatarPaint)

        //resize rect
        val half = (borderWidth / 2).toInt()
        viewRect.inset(half, half)

        canvas?.drawOval(viewRect.toRectF(), borderPaint)

    }

    private fun resolveDefaultSize(spec: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_SIZE).toInt()
            MeasureSpec.AT_MOST -> MeasureSpec.getSize(spec)
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            else -> MeasureSpec.getSize(spec)
        }
    }

    private fun setup() {
        with(borderPaint) {
            style = Paint.Style.STROKE
            strokeWidth = borderWidth
            color = borderColor
        }
    }

    private fun prepareShader(w: Int, h: Int) {
        val srcBm = drawable.toBitmap(w, h, Bitmap.Config.ARGB_8888)
        avatarPaint.shader = BitmapShader(srcBm,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
    }
}