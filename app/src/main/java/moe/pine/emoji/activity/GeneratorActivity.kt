package moe.pine.emoji.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_generator.*
import kotlinx.android.synthetic.main.activity_generator_result.*
import moe.pine.emoji.activity.binding.clear
import moe.pine.emoji.activity.binding.updateUI
import moe.pine.emoji.components.ActionBarBackButtonComponent
import moe.pine.emoji.components.SupportActionBarComponent
import moe.pine.emoji.fragment.generator.InputTextDialogFragment

/**
 * Activity for generator
 * Created by pine on Apr 20, 2017.
 */
class GeneratorActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent = Intent(context, GeneratorActivity::class.java)
    }

    private val actionBar by lazy { SupportActionBarComponent(this) }
    private val backButton by lazy { ActionBarBackButtonComponent(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(moe.pine.emoji.R.layout.activity_generator)
        this.actionBar.onCreate()

        this.clear()
        this.updateUI()

        view_emoji_text.setOnClickListener {
            val dialog = InputTextDialogFragment.newInstance(text_view_generator_text.text.toString())
            this.supportFragmentManager?.let { dialog.show(it, null) }
        }
    }

    override fun onResume() {
        super.onResume()

        // val dialog = InputTextDialogFragment.newInstance()
        // this.supportFragmentManager?.let { dialog.show(it, null) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return this.backButton.onOptionsItemSelected(item) or super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        this.view_generator_share_button.onRequestPermissionResult(permissions, grantResults)
    }
}