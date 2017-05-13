package moe.pine.emoji.fragment.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.pine.emoji.R

/**
 * Fragment for slack team list
 * Created by pine on May 14, 2017.
 */
class TeamListFragment : Fragment() {
    companion object {
        fun newInstance() = TeamListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting_team_list, container, false)
    }


}