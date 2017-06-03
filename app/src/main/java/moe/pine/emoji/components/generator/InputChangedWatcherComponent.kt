package moe.pine.emoji.components.generator

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.dialog_register.view.*
import moe.pine.emoji.view.generator.RegisterDialogView

/**
 * Component for input changed watcher
 * Created by pine on May 14, 2017.
 */
class InputChangedWatcherComponent(
        val view: RegisterDialogView
) : TextWatcher, AdapterView.OnItemSelectedListener {
    private val isOk: Boolean
        get() {
            return this.view.previewUri.isNotEmpty() &&
                    this.view.edit_text_generator_emoji_name.text.isNotEmpty() &&
                    this.view.spinner_generator_teams.selectedItemPosition >= 0
        }

    fun onFinishInflate() {
        this.view.spinner_generator_teams.onItemSelectedListener = this
        this.view.edit_text_generator_emoji_name.addTextChangedListener(this)
        this.updateUI()
    }

    override fun afterTextChanged(s: Editable?) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        this.updateUI()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.updateUI()
    }

    private fun updateUI() {
        this.view.button_generator_register_button.isEnabled = this.isOk
    }
}