package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.R.attr.top
import android.R.attr.bottom
import android.opengl.ETC1.getHeight


/* Скрытие клавиатуры */
fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    /* Определение текущего фокуса */
    var view = this.getCurrentFocus()

    /* Скрытие клавиатуры */
    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val rootView = this.currentFocus
    val rect = Rect().apply { rootView.getWindowVisibleDisplayFrame(this) }

    val screenHeight = rootView.height

    val keypadHeight = screenHeight - rect.bottom

    if (keypadHeight > screenHeight * 0.15) {
        return true
    }
    return false

}