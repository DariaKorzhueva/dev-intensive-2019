package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/* Скрытие клавиатуры */
fun Activity.hideKeyboard(){
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    /* Определение текущего фокуса */
    var view = this.getCurrentFocus()

    /* Скрытие клавиатуры */
    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
}