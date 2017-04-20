package moe.pine.emoji.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import moe.pine.emoji.R
import moe.pine.emoji.components.SupportActionBarComponent

/**
 * Activity for Setting
 * Created by pine on Apr 21, 2017.
 */
class SettingActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent = Intent(context, SettingActivity::class.java)
    }

    val actionBar by lazy { SupportActionBarComponent(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_generator)
        this.actionBar.onCreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}