package moe.pine.emoji.components.setting

import android.os.Bundle
import android.support.annotation.UiThread
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_setting_add_team.*
import moe.pine.emoji.fragment.setting.AddTeamFragment
import moe.pine.emoji.fragment.setting.binding.email
import moe.pine.emoji.fragment.setting.binding.password
import moe.pine.emoji.fragment.setting.binding.teamDomain
import moe.pine.emoji.model.internal.SlackTeam

/**
 * Component for setting saver
 * Created by pine on May 14, 2017.
 */

class SettingSaverComponent(
        val fragment: AddTeamFragment
) {
    private lateinit var realm: Realm

    @UiThread
    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.realm = Realm.getDefaultInstance()
        this.fragment.button_setting_add_team.setOnClickListener { this.save() }
    }

    @UiThread
    fun onDestroyView() {
        this.realm.close()
    }

    @UiThread
    private fun save() {
        val slackTeam = SlackTeam(
                domain = this.fragment.teamDomain,
                email = this.fragment.email,
                password = this.fragment.password
        )
        this.realm.beginTransaction()
        this.realm.copyToRealmOrUpdate(slackTeam)
        this.realm.commitTransaction()
    }
}