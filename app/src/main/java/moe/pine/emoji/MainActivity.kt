package moe.pine.emoji

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

/**
 * Activity for main
 * Created by pine on Apr 18, 2017.
 */
class MainActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
    }
}