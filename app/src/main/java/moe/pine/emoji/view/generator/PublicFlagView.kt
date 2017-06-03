package moe.pine.emoji.view.generator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_generator_public_flag.view.*

/**
 * View for public flag
 * Created by pine on 2017/06/03.
 */

class PublicFlagView : LinearLayout {
    var checked: Boolean
        private set(value) {
            this.switch_public_flag.isChecked = value
        }
        get() = this.switch_public_flag.isChecked

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @SuppressWarnings("unused")
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onFinishInflate() {
        super.onFinishInflate()

        this.setOnClickListener { this.onSwitchToggle() }
        this.switch_public_flag.setOnCheckedChangeListener { buttonView, isChecked ->
            this.onCheckedChanged(isChecked)
        }
    }

    private fun onSwitchToggle() {
        this.checked = !this.checked
    }

    private fun onCheckedChanged(isChecked: Boolean) {
        if (isChecked) {
            this.text_view_public_flag_on.visibility = View.VISIBLE
            this.text_view_public_flag_off.visibility = View.GONE
        } else {
            this.text_view_public_flag_on.visibility = View.GONE
            this.text_view_public_flag_off.visibility = View.VISIBLE
        }
    }
}