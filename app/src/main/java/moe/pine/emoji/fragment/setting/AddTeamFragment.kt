package moe.pine.emoji.fragment.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.pine.emoji.R

/**
 * Created by pine on May 14, 2017.
 */

class AddTeamFragment : Fragment() {
    companion object {
        fun newInstance() = AddTeamFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting_add_team, container, false)
    }
}