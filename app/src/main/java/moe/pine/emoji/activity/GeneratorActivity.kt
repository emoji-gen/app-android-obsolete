package moe.pine.emoji.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_generator.*
import kotlinx.android.synthetic.main.activity_generator_result.*
import moe.pine.emoji.activity.binding.clear
import moe.pine.emoji.components.SupportActionBarComponent
import moe.pine.emoji.fragment.InputTextDialogFragment

/**
 * Activity for generator
 * Created by pine on Apr 20, 2017.
 */
class GeneratorActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent = Intent(context, GeneratorActivity::class.java)
    }

    val actionBar by lazy { SupportActionBarComponent(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(moe.pine.emoji.R.layout.activity_generator)
        this.actionBar.onCreate()
        this.clear()

        Glide.with(this)
                .load("https://emoji.pine.moe/emoji?align=center&back_color=FFFFFF00&color=EC71A1FF&font=notosans-mono-bold&public_fg=true&size_fixed=false&stretch=true&text=%E7%B5%B5%E6%96%87%0A%E5%AD%97%E3%80%82")
                .into(image_view_preview)

        view_emoji_text.setOnClickListener {
            val dialog = InputTextDialogFragment.newInstance(text_view_emoji_text.text.toString())
            this.supportFragmentManager?.let { dialog.show(it, null) }
        }
    }

    override fun onResume() {
        super.onResume()

        val dialog = InputTextDialogFragment.newInstance()
        this.supportFragmentManager?.let { dialog.show(it, null) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}