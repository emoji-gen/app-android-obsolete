package moe.pine.emoji.components.main

import android.support.v7.app.AppCompatActivity
import com.squareup.otto.Subscribe
import moe.pine.emoji.R
import moe.pine.emoji.fragment.main.MyHistoryFragment
import moe.pine.emoji.fragment.main.OurHistoryFragment
import moe.pine.emoji.model.event.main.ShowMyHistoryEvent
import moe.pine.emoji.model.event.main.ShowOurHistoryEvent
import moe.pine.emoji.util.eventBus

/**
 * Component for fragment switcher
 * Created by pine on 2017/06/01.
 */
class FragmentSwitcherComponent(
        val activity: AppCompatActivity
) {

    fun onCreate() {
        this.eventBus.register(this)
        this.showMyHistory()
    }

    fun onDestroy() {
        this.eventBus.unregister(this)
    }

    @Subscribe
    fun onShowMyHistory(event: ShowMyHistoryEvent) {
        this.showMyHistory()
    }

    @Subscribe
    fun onShowOurHistory(event: ShowOurHistoryEvent) {
        this.showOurHistory()
    }

    private fun showMyHistory() {
        val transaction = this.activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, MyHistoryFragment.newInstance())
        transaction.commitAllowingStateLoss()
    }

    private fun showOurHistory() {
        val transaction = this.activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, OurHistoryFragment.newInstance())
        transaction.commitAllowingStateLoss()
    }
}