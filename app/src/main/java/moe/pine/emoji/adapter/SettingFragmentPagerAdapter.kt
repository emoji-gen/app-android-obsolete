package moe.pine.emoji.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import moe.pine.emoji.R
import moe.pine.emoji.fragment.setting.AddTeamFragment

/**
 * PagerAdapter for setting fragments
 * Created by pine on May 14, 2017.
 */

class SettingFragmentPagerAdapter(
        fm: FragmentManager?,
        val context: Context
) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AddTeamFragment.newInstance()
            1 -> AddTeamFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> this.context.getString(R.string.setting_add_team_title)
            1 -> this.context.getString(R.string.setting_team_list_title)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getCount(): Int = 2
}
