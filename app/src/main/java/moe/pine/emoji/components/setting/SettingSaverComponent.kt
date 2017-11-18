package moe.pine.emoji.components.setting

import android.content.Context
import android.os.Bundle
import android.support.annotation.UiThread
import kotlinx.android.synthetic.main.fragment_setting_add_team.*
import moe.pine.emoji.fragment.setting.AddTeamFragment
import moe.pine.emoji.fragment.setting.binding.email
import moe.pine.emoji.fragment.setting.binding.password
import moe.pine.emoji.fragment.setting.binding.team
import moe.pine.emoji.task.AuthAndSaveTask
import moe.pine.emoji.util.hideSoftInput

/**
 * Component for setting saver
 * Created by pine on May 14, 2017.
 */

class SettingSaverComponent(
        private val fragment: AddTeamFragment
) {
    private val context: Context
        get() = this.fragment.context!!

    @UiThread
    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.fragment.button_setting_add_team.setOnClickListener {
            this.fragment.hideSoftInput()
            this.add()
        }
    }

    @UiThread
    private fun add() {
        val arguments = AuthAndSaveTask.Arguments(
                team = this.fragment.team,
                email = this.fragment.email,
                password = this.fragment.password
        )
        val task = AuthAndSaveTask(this.context, arguments)
        task.execute()
    }
}