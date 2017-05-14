package moe.pine.emoji.components.generator

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.dialog_register.view.*
import moe.pine.emoji.task.RegisterAndSaveTask
import moe.pine.emoji.util.hideSoftInput
import moe.pine.emoji.view.generator.RegisterDialogView
import moe.pine.emoji.view.generator.binding.emojiName
import moe.pine.emoji.view.generator.binding.team

/**
 * Component for emoji register
 * Created by pine on May 14, 2017.
 */
class EmojiRegisterComponent(
        val view: RegisterDialogView
) {
    private val context: Context
        get() = this.view.context

    fun onFinishInflate() {
        this.view.button_generator_register_button.setOnClickListener { this.register() }
    }

    private fun register() {
        this.view.hideSoftInput()

        val team = this.view.team ?: return
        val arguments = RegisterAndSaveTask.Arguments(
                team = team,
                emojiName = this.view.emojiName,
                emojiUri = this.view.emojiUri
        )
        val task = RegisterAndSaveTask(this.context, arguments)
        task.execute()
    }
}