package moe.pine.emoji.fragment.setting.binding

import kotlinx.android.synthetic.main.fragment_setting_add_team.*
import moe.pine.emoji.fragment.setting.AddTeamFragment

/**
 * Bindings for AddTeamFragment
 * Created by pine on May 14, 2017.
 */

val AddTeamFragment.teamDomain: String
    get() = this.edit_text_setting_team_domain.text.toString()


val AddTeamFragment.email: String
    get() = this.edit_text_setting_email.text.toString()


val AddTeamFragment.password: String
    get() = this.edit_text_setting_password.text.toString()
