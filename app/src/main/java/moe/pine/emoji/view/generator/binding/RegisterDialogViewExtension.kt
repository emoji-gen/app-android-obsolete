package moe.pine.emoji.view.generator.binding

import kotlinx.android.synthetic.main.dialog_register.view.*
import moe.pine.emoji.model.realm.SlackTeam
import moe.pine.emoji.view.generator.RegisterDialogView
import java.lang.IndexOutOfBoundsException

/**
 * Extensions for RegisterDialogView
 * Created by pine on May 14, 2017.
 */

val RegisterDialogView.team: String?
    get() {
        try {
            val spinner = this.spinner_generator_teams
            val team = spinner.selectedItem as? SlackTeam
            return team?.team
        } catch (e: IndexOutOfBoundsException) {
            return null
        }
    }


val RegisterDialogView.emojiName: String
    get() = this.edit_text_generator_emoji_name.text.toString()

