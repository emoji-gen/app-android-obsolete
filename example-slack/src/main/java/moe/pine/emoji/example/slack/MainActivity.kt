package moe.pine.emoji.example.slack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MainActivity
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        val adapter = MainPagerAdapter(this.supportFragmentManager)
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }
}
