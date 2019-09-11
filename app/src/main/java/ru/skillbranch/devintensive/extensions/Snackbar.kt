package ru.skillbranch.devintensive.extensions

import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.google.android.material.snackbar.Snackbar

/* Установка фона для снэкбара */
fun Snackbar.setBackgroundDrawable(@DrawableRes drawable: Int): Snackbar {
    this.view.setBackgroundResource(drawable)
    return this
}

/* Установка цвета текста для снэкбара */
fun Snackbar.setTextColor(@ColorInt textColor: Int): Snackbar {
    this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        .setTextColor(textColor)
    return this
}