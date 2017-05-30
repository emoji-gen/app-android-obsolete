package moe.pine.emoji.activity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main_navigation_view.*
import moe.pine.emoji.R
import moe.pine.emoji.adapter.main.HistoryRecyclerAdapter
import moe.pine.emoji.components.common.ActionBarDrawerToggleComponent
import moe.pine.emoji.components.common.SupportActionBarComponent
import moe.pine.emoji.lib.emoji.ApiCallback
import moe.pine.emoji.lib.emoji.ApiClient
import moe.pine.emoji.lib.emoji.model.History
import java.io.IOException

/**
 * Activity for main
 * Created by pine on Apr 18, 2017.
 */
class MainActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    val toggle by lazy { ActionBarDrawerToggleComponent(this, this.activity_main, R.string.app_name, R.string.app_name) }
    val actionBar by lazy { SupportActionBarComponent(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(moe.pine.emoji.R.layout.activity_main)
        this.toggle.onCreate()
        this.actionBar.onCreate()
        this.navigation_view_main.setupView(activity_main)

        val adapter = HistoryRecyclerAdapter(this)
        this.recycler_view.adapter = adapter

        val client = ApiClient()
        client.fetchHistories(object : ApiCallback<List<History>> {
            override fun onFailure(e: IOException?) {
                super.onFailure(e)
            }

            override fun onResponse(response: List<History>) {
                super.onResponse(response)
                adapter.histories = response
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return this.toggle.onOptionsItemSelected(item) or super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        this.toggle.onPostCreate(savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        this.toggle.onConfigurationChanged(newConfig)
    }
}