package moe.pine.emoji.view.generator

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.dialog_text_input.view.*

/**
 * Dialog view for text input
 * Created by pine on May 6, 2017.
 */
class InputTextDialogView : LinearLayout {
    var onOkClickListener: (() -> Unit) = {}

    var inputText: String
        set(str) {
            edit_text_text_input.setText(str)
            edit_text_text_input.setSelection(str.length)
        }
        get() = edit_text_text_input.text.toString()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        button_text_input_ok.setOnClickListener { this.onOkClickListener() }
        edit_text_text_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_text_input_ok.isEnabled = this@InputTextDialogView.inputText.isNotEmpty()
            }
        })
    }
}