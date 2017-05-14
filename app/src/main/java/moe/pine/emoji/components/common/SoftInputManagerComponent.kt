package moe.pine.emoji.components.common

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Component for soft input manager
 * Created by pine on May 14, 2017.
 */

class SoftInputManagerComponent(
        val view: View
) {
    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.view.setOnTouchListener { _, _ ->
            val manager = this.view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(this.view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            this.view.requestFocus()
            false
        }
    }
}