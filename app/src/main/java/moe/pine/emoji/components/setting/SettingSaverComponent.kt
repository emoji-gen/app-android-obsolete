package moe.pine.emoji.components.setting

import android.content.Context
import android.os.Bundle
import android.support.annotation.UiThread
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_setting_add_team.*
import moe.pine.emoji.fragment.setting.AddTeamFragment
import moe.pine.emoji.fragment.setting.binding.clear
import moe.pine.emoji.fragment.setting.binding.email
import moe.pine.emoji.fragment.setting.binding.password
import moe.pine.emoji.fragment.setting.binding.team
import moe.pine.emoji.model.event.TeamUpdateEvent
import moe.pine.emoji.model.realm.SlackTeam
import moe.pine.emoji.task.AuthTask
import moe.pine.emoji.util.eventBus
import moe.pine.emoji.util.hideSoftInput

/**
 * Component for setting saver
 * Created by pine on May 14, 2017.
 */

class SettingSaverComponent(
        private val fragment: AddTeamFragment
) {
    private val context: Context
        get() = this.fragment.context

    @UiThread
    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.fragment.button_setting_add_team.setOnClickListener { this.add() }
    }

    private fun add() {
        val task = AuthTask(this.context)
        val arguments = AuthTask.Arguments(
                team = this.fragment.team,
                email = this.fragment.email,
                password = this.fragment.password
        )
        task.execute(arguments)
    }

    @UiThread
    private fun save() {
        Realm.getDefaultInstance().use { realm ->
            val slackTeam = SlackTeam(
                    team = this.fragment.team,
                    email = this.fragment.email,
                    password = this.fragment.password
            )
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(slackTeam)
            realm.commitTransaction()

            this.eventBus.post(TeamUpdateEvent())
            this.fragment.clear()
            this.fragment.hideSoftInput()
        }
    }
}