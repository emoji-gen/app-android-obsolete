package moe.pine.emoji.adapter.generator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import moe.pine.emoji.R
import moe.pine.emoji.model.realm.SlackTeam
import moe.pine.emoji.view.generator.TeamSpinnerItemView

/**
 * Adapter for generator's team list
 * Created by pine on May 14, 2017.
 */
class GeneratorTeamListAdapter(
        context: Context
) : ArrayAdapter<SlackTeam>(context, R.layout.view_empty) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(this.context)

        if (super.getCount() == 0) {
            return inflater.inflate(R.layout.view_generator_team_spinner_empty_item, parent, false)
        }

        var view = convertView as? TeamSpinnerItemView
        if (view == null) {
            view = inflater.inflate(R.layout.view_generator_team_spinner_item, parent, false) as TeamSpinnerItemView
        }

        val item = this.getItem(position)
        view.team = item

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(this.context)

        if (super.getCount() == 0) {
            return inflater.inflate(R.layout.view_empty, parent, false)
        }

        var view = convertView as? TeamSpinnerItemView
        if (view == null) {
            view = inflater.inflate(R.layout.view_generator_team_spinner_dropdown_item, parent, false) as TeamSpinnerItemView
        }

        val item = this.getItem(position)
        view.team = item

        return view
    }

    override fun getCount(): Int {
        return Math.max(super.getCount(), 1)
    }

    fun replaceAll(items: List<SlackTeam>) {
        this.clear()
        this.addAll(items)
    }
}