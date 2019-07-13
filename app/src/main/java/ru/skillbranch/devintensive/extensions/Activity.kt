package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.util.TypedValue
import android.view.View
import kotlin.math.round

/* Скрытие клавиатуры */
fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    /* Определение текущего фокуса */
    var view = this.getCurrentFocus()

    /* Скрытие клавиатуры */
    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
}

/* Конвертация dp в px */
fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.resources.displayMetrics)
}

/* Проверка конфигурации клавиатуры */
fun Activity.checkKeyboard(): Boolean {
    val rootView = this.findViewById<View>(android.R.id.content)
    val r = Rect()

    rootView.getWindowVisibleDisplayFrame(r)

    val heightDiff = rootView.height - r.height()
    val marginOfError = round(this.convertDpToPx(50F))

    return heightDiff > marginOfError
}

/* Проврека на открытую клавиатуры */
fun Activity.isKeyboardOpen(): Boolean {
    return checkKeyboard()
}

/* Проврека на закрытую клавиатуры */
fun Activity.isKeyboardClosed(): Boolean {
    return !checkKeyboard()
}