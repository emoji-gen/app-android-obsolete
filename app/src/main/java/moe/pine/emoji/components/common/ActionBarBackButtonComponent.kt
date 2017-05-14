package moe.pine.emoji.components.common

import android.R
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Component for ActionBar back button
 * Created by pine on Apr 21, 2017.
 */
class ActionBarBackButtonComponent(val activity: AppCompatActivity) {
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            this.activity.onBackPressed()
            return true
        }

        return false
    }
}