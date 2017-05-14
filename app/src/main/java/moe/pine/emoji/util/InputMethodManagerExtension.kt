package moe.pine.emoji.util

import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Utilities for InputMethodManager
 * Created by pine on May 14, 2017.
 */

fun View.hideSoftInput() {
    val manager = this.context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(this.windowToken, android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS)
    this.requestFocus()
}

fun Fragment.hideSoftInput() {
    this.view?.hideSoftInput()
}