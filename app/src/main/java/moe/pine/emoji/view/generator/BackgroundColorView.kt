package moe.pine.emoji.view.generator

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_generator_background_color.view.*
import moe.pine.emoji.fragment.generator.SelectColorDialogFragment
import moe.pine.emoji.util.rgba.toColor
import moe.pine.emoji.util.rgba.toRgba

/**
 * BackgroundColorView
 * Created by pine on May 6, 2017.
 */
class BackgroundColorView : LinearLayout {
    var color: Int
        set(value) {
            text_view_generator_background_color.text = value.toRgba()
            view_generator_background_color_square.setBackgroundColor(value)
        }
        get() = text_view_generator_background_color.text.toString().toColor()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()

        view_generator_background_color.setOnClickListener {
            val dialog = SelectColorDialogFragment.newInstance(this.color, isBackground = true)
            val activity = this.context as AppCompatActivity
            activity.supportFragmentManager?.let { dialog.show(it, null) }
        }
    }
}