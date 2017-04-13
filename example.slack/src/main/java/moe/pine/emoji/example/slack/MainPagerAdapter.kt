package moe.pine.emoji.example.slack

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * MainPagerAdapter
 * Created by pine on Apr 13, 2017.
 */
class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AuthFragment.newInstance()
            1 -> AddEmojiFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Auth"
            1 -> "Add Emoji"
            else -> throw IllegalArgumentException()
        }
    }

    override fun getCount(): Int = 2
}