package moe.pine.emoji.view.generator

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_generator_emoji_text.view.*
import moe.pine.emoji.R
import moe.pine.emoji.fragment.generator.InputTextDialogFragment

/**
 * View for emoji text
 * Created by pine on 2017/07/01.
 */

class EmojiTextView : LinearLayout {
    var text: String
        set(str) {
            if (str.isNotEmpty()) {
                text_view_generator_text.text = str
            } else {
                text_view_generator_text.setText(R.string.generator_text_placeholder)
            }
        }
        get() = text_view_generator_text.text.toString()


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()

        this.view_emoji_text.setOnClickListener {
            val dialog = InputTextDialogFragment.newInstance(this.text_view_generator_text.text.toString())
            val activity = this.context as AppCompatActivity
            activity.supportFragmentManager?.let { dialog.show(it, null) }
        }
    }
}