package moe.pine.emoji.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_setting.*
import moe.pine.emoji.R
import moe.pine.emoji.adapter.SettingFragmentPagerAdapter
import moe.pine.emoji.components.ActionBarBackButtonComponent
import moe.pine.emoji.components.SupportActionBarComponent

/**
 * Activity for Setting
 * Created by pine on Apr 21, 2017.
 */
class SettingActivity : AppCompatActivity() {
    companion object {
        private val IS_FOCUS_KEY = "isFocus"

        fun createIntent(context: Context, focus: Boolean = false): Intent {
            return Intent(context, SettingActivity::class.java).also { intent ->
                intent.putExtra(IS_FOCUS_KEY, focus)
            }
        }
    }

    private val actionBar by lazy { SupportActionBarComponent(this) }
    private val backButton by lazy { ActionBarBackButtonComponent(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_setting)
        this.actionBar.onCreate()

        val isFocus = this.intent.extras.getBoolean(IS_FOCUS_KEY, false)
        val adapter = SettingFragmentPagerAdapter(this.supportFragmentManager, this, isFocus)
        this.view_pager.adapter = adapter
        this.view_pager.offscreenPageLimit = 2
        this.tab_layout.setupWithViewPager(this.view_pager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return this.backButton.onOptionsItemSelected(item) or super.onOptionsItemSelected(item)
    }
}