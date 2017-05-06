package moe.pine.emoji.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_generator.*
import kotlinx.android.synthetic.main.activity_generator_result.*
import kotlinx.android.synthetic.main.view_generator_font.*
import moe.pine.emoji.activity.binding.clear
import moe.pine.emoji.components.ActionBarBackButtonComponent
import moe.pine.emoji.components.SupportActionBarComponent
import moe.pine.emoji.fragment.generator.InputTextDialogFragment
import moe.pine.emoji.fragment.generator.SelectFontDialogFragment

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

        Glide.with(this)
                .load("https://emoji.pine.moe/emoji?align=center&back_color=FFFFFF00&color=EC71A1FF&font=notosans-mono-bold&public_fg=true&size_fixed=false&stretch=true&text=%E7%B5%B5%E6%96%87%0A%E5%AD%97%E3%80%82")
                .into(image_view_preview)

        view_emoji_text.setOnClickListener {
            val dialog = InputTextDialogFragment.newInstance(text_view_emoji_text.text.toString())
            this.supportFragmentManager?.let { dialog.show(it, null) }
        }
        view_emoji_font.setOnClickListener {
            val fonts = arrayListOf("a", "b", "c")
            val dialog = SelectFontDialogFragment.newInstance(fonts)
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
}