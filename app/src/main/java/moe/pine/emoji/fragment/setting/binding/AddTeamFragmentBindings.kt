package moe.pine.emoji.fragment.setting.binding

import kotlinx.android.synthetic.main.fragment_setting_add_team.*
import moe.pine.emoji.fragment.setting.AddTeamFragment

/**
 * Bindings for AddTeamFragment
 * Created by pine on May 14, 2017.
 */

fun AddTeamFragment.clear() {
    this.team = ""
    this.email = ""
    this.password = ""
}

var AddTeamFragment.team: String
    get() = this.edit_text_setting_team.text.toString()
    set(value) {
        this.edit_text_setting_team.setText(value)
    }

var AddTeamFragment.email: String
    get() = this.edit_text_setting_email.text.toString()
    set(value) {
        this.edit_text_setting_email.setText(value)
    }

var AddTeamFragment.password: String
    get() = this.edit_text_setting_password.text.toString()
    set(value) {
        this.edit_text_setting_password.setText(value)
    }