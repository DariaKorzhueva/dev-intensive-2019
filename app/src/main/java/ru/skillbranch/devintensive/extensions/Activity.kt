package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.R.attr.top
import android.R.attr.bottom
import android.opengl.ETC1.getHeight



/* Скрытие клавиатуры */
fun Activity.hideKeyboard(){
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    /* Определение текущего фокуса */
    var view = this.getCurrentFocus()

    /* Скрытие клавиатуры */
    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
}

fun Activity.isKeyboardOpen():Boolean{
    var view = this.getCurrentFocus().rootView

    val r = Rect()
    view.getWindowVisibleDisplayFrame(r)

    val screenHeight = view.getRootView().getHeight()
    val heightDifference = screenHeight - (r.bottom - r.top)

    if(heightDifference > 100){
        return true
    }

    return false
}