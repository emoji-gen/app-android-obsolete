package moe.pine.emoji.components.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import com.squareup.otto.Subscribe
import io.realm.Realm
import io.realm.Sort
import kotlinx.android.synthetic.main.fragment_setting_team_list.*
import moe.pine.emoji.R
import moe.pine.emoji.adapter.SettingTeamListAdapter
import moe.pine.emoji.model.event.TeamDeleteEvent
import moe.pine.emoji.model.event.TeamUpdateEvent
import moe.pine.emoji.model.realm.SlackTeam
import moe.pine.emoji.util.eventBus

/**
 * Component for setting team list
 * Created by pine on May 14, 2017.
 */

class SettingTeamListComponent(
        val fragment: Fragment
) {
    private lateinit var realm: Realm

    private val teams: List<SlackTeam>
        get() = this.realm.where(SlackTeam::class.java).findAllSorted("domain", Sort.ASCENDING)

    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.realm = Realm.getDefaultInstance()
        this.eventBus.register(this)

        val adapter = SettingTeamListAdapter(this.fragment.context)
        this.fragment.list_view_setting.adapter = adapter

        this.update()
    }

    fun onDestroyView() {
        this.eventBus.unregister(this)
        this.realm.close()
    }

    @Subscribe
    fun onTeamDelete(event: TeamDeleteEvent) {
        this.removeTeam(event.domain)
        this.update()
    }

    @Subscribe
    fun onTeamUpdate(event: TeamUpdateEvent) {
        this.update()
    }

    private fun update() {
        val adapter = this.fragment.list_view_setting.adapter as SettingTeamListAdapter
        adapter.replaceAll(this.teams)
    }

    private fun removeTeam(domain: String) {
        val team: SlackTeam? = this.realm.where(SlackTeam::class.java)
                .equalTo("domain", domain)
                .findFirst()

        team ?: return
        this.realm.executeTransaction { team.deleteFromRealm() }
    }
}