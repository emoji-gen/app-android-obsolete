package moe.pine.emoji.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.UiThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.realm.Realm
import io.realm.Sort
import moe.pine.emoji.R
import moe.pine.emoji.model.realm.SlackTeam
import moe.pine.emoji.view.setting.TeamListItemView

/**
 * Adapter for setting team list
 * Created by pine on May 14, 2017.
 */
class SettingTeamListAdapter(
        context: Context,
        @LayoutRes val layoutId: Int = R.layout.view_setting_team_list_item
) : ArrayAdapter<SlackTeam>(context, layoutId) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(this.context)
        var view = convertView as? TeamListItemView
        if (view == null) {
            view = inflater.inflate(this.layoutId, parent, false) as TeamListItemView
        }

        val item = this.getItem(position)
        view.team = item

        return view
    }

    fun replaceAll(items: List<SlackTeam>) {
        this.clear()
        this.addAll(items)
    }
}