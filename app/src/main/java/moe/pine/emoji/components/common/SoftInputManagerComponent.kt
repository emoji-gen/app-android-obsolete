package moe.pine.emoji.components.common

import android.os.Bundle
import android.view.View
import moe.pine.emoji.util.hideSoftInput

/**
 * Component for soft input manager
 * Created by pine on May 14, 2017.
 */

class SoftInputManagerComponent(
        val view: View
) {
    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.view.setOnTouchListener { v, _ ->
            v.hideSoftInput()
            false
        }
    }
}