package moe.pine.emoji.activity.binding

import kotlinx.android.synthetic.main.activity_generator.*
import kotlinx.android.synthetic.main.activity_generator_result.*
import kotlinx.android.synthetic.main.view_generator_background_color.*
import kotlinx.android.synthetic.main.view_generator_text_color.*
import moe.pine.emoji.R
import moe.pine.emoji.activity.GeneratorActivity

/**
 * Extension for GeneratorActivity
 * Created by pine on May 6, 2017.
 */
fun GeneratorActivity.clear() {
    image_view_preview.setImageDrawable(null)
}


var GeneratorActivity.emojiText: String
    set(str) {
        if (str.isNotEmpty()) {
            text_view_emoji_text.text = str
        } else {
            text_view_emoji_text.setText(R.string.generator_text_placeholder)
        }
    }
    get() = text_view_emoji_text.text.toString()


var GeneratorActivity.textColor: Int
    set(value) {
        view_generator_text_color.color = value
    }
    get() = view_generator_text_color.color


var GeneratorActivity.backgroundColor: Int
    set(value) {
        view_generator_background_color.color = value
    }
    get() = view_generator_background_color.color