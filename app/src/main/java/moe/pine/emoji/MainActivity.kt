package moe.pine.emoji

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity for main
 * Created by pine on Apr 18, 2017.
 */
class MainActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)


        this.supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }

        this.drawerToggle = object : ActionBarDrawerToggle(
                this,
                activity_main,
                R.string.app_name,
                R.string.app_name
        ) {
            override fun onDrawerClosed(drawerView: View?) {
                supportInvalidateOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View?) {
                supportInvalidateOptionsMenu()
            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        activity_main.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (this.drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        this.drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        this.drawerToggle.onConfigurationChanged(newConfig)
    }
}