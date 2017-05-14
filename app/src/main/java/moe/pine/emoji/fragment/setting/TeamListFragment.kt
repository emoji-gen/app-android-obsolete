package moe.pine.emoji.fragment.setting

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.otto.Subscribe
import io.realm.Realm
import io.realm.Sort
import kotlinx.android.synthetic.main.fragment_setting_team_list.*
import moe.pine.emoji.R
import moe.pine.emoji.adapter.SettingTeamListAdapter
import moe.pine.emoji.components.setting.SettingTeamListComponent
import moe.pine.emoji.model.realm.SlackTeam

/**
 * Fragment for slack team list
 * Created by pine on May 14, 2017.
 */
class TeamListFragment : Fragment() {
    companion object {
        fun newInstance() = TeamListFragment()
    }

    private val teamList by lazy { SettingTeamListComponent(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting_team_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.teamList.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        this.teamList.onDestroyView()
        super.onDestroyView()
    }
}