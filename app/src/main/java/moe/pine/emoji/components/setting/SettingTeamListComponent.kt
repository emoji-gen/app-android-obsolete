package moe.pine.emoji.components.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import io.realm.Realm
import io.realm.Sort
import kotlinx.android.synthetic.main.fragment_setting_team_list.*
import moe.pine.emoji.adapter.SettingTeamListAdapter
import moe.pine.emoji.model.internal.SlackTeam

/**
 * Component for setting team list
 * Created by pine on May 14, 2017.
 */

class SettingTeamListComponent(
        val fragment: Fragment
) {
    private lateinit var realm: Realm

    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.realm = Realm.getDefaultInstance()

        val adapter = SettingTeamListAdapter(this.fragment.context)
        adapter.replaceAll(this.teams)

        this.fragment.list_view_setting.adapter = adapter
    }

    fun onDestroyView() {
        this.realm.close()
    }

    private val teams: List<SlackTeam>
        get() = this.realm.where(SlackTeam::class.java).findAllSorted("domain", Sort.ASCENDING)
}