package moe.pine.emoji.components.setting

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.fragment_setting_add_team.*
import moe.pine.emoji.fragment.setting.AddTeamFragment

/**
 * Component for input text watcher
 * Created by pine on May 14, 2017.
 */
class InputTextWatcherComponent(val fragment: AddTeamFragment) : TextWatcher {
    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.fragment.edit_text_setting_team.addTextChangedListener(this)
        this.fragment.edit_text_setting_email.addTextChangedListener(this)
        this.fragment.edit_text_setting_password.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        this.updateUI()
    }

    private fun updateUI() {
        this.fragment.button_setting_add_team.isEnabled = this.isOk
    }

    private val isOk: Boolean
        get() {
            return this.fragment.edit_text_setting_team.text.isNotEmpty() &&
                    this.fragment.edit_text_setting_email.text.isNotEmpty() &&
                    this.fragment.edit_text_setting_password.text.isNotEmpty()
        }
}