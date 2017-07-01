package moe.pine.emoji.view.generator

import android.content.Context
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_generator_font.view.*
import moe.pine.emoji.fragment.generator.SelectFontDialogFragment
import moe.pine.emoji.lib.emoji.JsonDeserializer
import moe.pine.emoji.value.LogicValues

/**
 * View for font list
 * Created by pine on May 7, 2017.
 */
class FontListView : LinearLayout {
    var fontName: String
        set(value) {
            text_view_generator_font_name.text = value
        }
        get() = text_view_generator_font_name.text.toString()

    var fontKey: String = ""

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()

        if (this.isInEditMode) return

        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        val fonts = JsonDeserializer.fontsFromJson(pref.getString(LogicValues.SharedPreferences.FONTS_KEY, "[]"))

        view_generator_font.setOnClickListener {
            val dialog = SelectFontDialogFragment.newInstance(fonts)
            val activity = this.context as AppCompatActivity
            activity.supportFragmentManager?.let { dialog.show(it, null) }
        }

        this.fontName = fonts.firstOrNull()?.name.orEmpty()
        this.fontKey = fonts.firstOrNull()?.key.orEmpty()
    }
}