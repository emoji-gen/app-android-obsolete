package moe.pine.emoji.activity.binding

import android.net.Uri
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_generator.*
import kotlinx.android.synthetic.main.activity_generator_result.*
import kotlinx.android.synthetic.main.view_generator_background_color.*
import kotlinx.android.synthetic.main.view_generator_font.*
import kotlinx.android.synthetic.main.view_generator_public_flag.*
import kotlinx.android.synthetic.main.view_generator_text_color.*
import moe.pine.emoji.R
import moe.pine.emoji.activity.GeneratorActivity
import moe.pine.emoji.util.rgba.toRgba

/**
 * Extension for GeneratorActivity
 * Created by pine on May 6, 2017.
 */
fun GeneratorActivity.clear() {
    image_view_preview.setImageDrawable(null)
}


fun GeneratorActivity.updateUI() {
    Glide.with(this)
            .load(this.previewUri)
            .fitCenter()
            .into(this.image_view_preview)
}


var GeneratorActivity.text: String
    set(str) {
        val changed = this.text_view_generator_text.text.toString() != str

        if (str.isNotEmpty()) {
            text_view_generator_text.text = str
        } else {
            text_view_generator_text.setText(R.string.generator_text_placeholder)
        }

        if (changed) this.updateUI()
    }
    get() = text_view_generator_text.text.toString()


var GeneratorActivity.fontName: String
    set(value) {
        view_generator_font.fontName = value
    }
    get() = view_generator_font.fontName


var GeneratorActivity.fontKey: String
    set(value) {
        val changed = this.view_generator_font.fontKey != value
        this.view_generator_font.fontKey = value
        if (changed) this.updateUI()
    }
    get() = view_generator_font.fontKey


var GeneratorActivity.textColor: Int
    set(value) {
        val changed = this.view_generator_text_color.color != value
        this.view_generator_text_color.color = value
        if (changed) this.updateUI()
    }
    get() = view_generator_text_color.color


var GeneratorActivity.backgroundColor: Int
    set(value) {
        val changed = this.view_generator_background_color.color != value
        this.view_generator_background_color.color = value
        if (changed) this.updateUI()
    }
    get() = view_generator_background_color.color


val GeneratorActivity.publicFlag: Boolean
    get() = this.view_generator_public_flag.checked


val GeneratorActivity.previewUri: Uri
    get() {
        return Uri.Builder()
                .scheme("https")
                .authority("emoji.pine.moe")
                .path("emoji")
                .appendQueryParameter("text", this.text)
                .appendQueryParameter("font", this.fontKey)
                .appendQueryParameter("color", this.textColor.toRgba())
                .appendQueryParameter("back_color", this.backgroundColor.toRgba())
                .build()
    }


val GeneratorActivity.downloadUri: Uri
    get() {
        return Uri.Builder()
                .scheme("https")
                .authority("emoji.pine.moe")
                .path("emoji_download")
                .appendQueryParameter("text", this.text)
                .appendQueryParameter("font", this.fontKey)
                .appendQueryParameter("color", this.textColor.toRgba())
                .appendQueryParameter("back_color", this.backgroundColor.toRgba())
                .appendQueryParameter("public_fg", if (this.publicFlag) "true" else "false")
                .build()
    }