package moe.pine.emoji.adapter.setting

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import moe.pine.emoji.R
import moe.pine.emoji.fragment.setting.AddTeamFragment
import moe.pine.emoji.fragment.setting.TeamListFragment

/**
 * PagerAdapter for setting fragments
 * Created by pine on May 14, 2017.
 */

class SettingFragmentPagerAdapter(
        fm: FragmentManager?,
        private val context: Context,
        private val isInitialFocus: Boolean
) : FragmentPagerAdapter(fm) {
    private var isFocus: Boolean = this.isInitialFocus

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AddTeamFragment.newInstance(this.isFocus).also { this.isFocus = false }
            1 -> TeamListFragment.newInstance()
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
