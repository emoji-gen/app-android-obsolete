package moe.pine.emoji.components.common

import android.support.v7.app.AppCompatActivity

/**
 * Component for SupportActionBar
 * Created by pine on Apr 21, 2017.
 */
class SupportActionBarComponent(
        val activity: AppCompatActivity
) {
    fun onCreate() {
        this.activity.supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }
    }
}