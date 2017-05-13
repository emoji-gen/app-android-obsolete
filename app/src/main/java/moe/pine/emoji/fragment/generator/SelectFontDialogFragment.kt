package moe.pine.emoji.fragment.generator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import moe.pine.emoji.activity.GeneratorActivity
import moe.pine.emoji.activity.binding.fontKey
import moe.pine.emoji.activity.binding.fontName
import moe.pine.emoji.lib.emoji.model.Font


/**
 * Fragment for font selection dialog
 * Created by pine on May 6, 2017.
 */

class SelectFontDialogFragment : DialogFragment() {
    companion object {
        val FONT_NAMES_KEY = "fontNames"
        val FONT_KEYS_KEY = "fontKeys"

        fun newInstance(fonts: List<Font>): SelectFontDialogFragment {
            return SelectFontDialogFragment().also { fragment ->
                val arguments = Bundle()
                val fontNames = fonts.map(Font::name).toTypedArray()
                val fontKeys = fonts.map(Font::key).toTypedArray()
                arguments.putStringArray(FONT_NAMES_KEY, fontNames)
                arguments.putStringArray(FONT_KEYS_KEY, fontKeys)
                fragment.arguments = arguments
            }
        }
    }

    private lateinit var fontNames: Array<String>
    private lateinit var fontKeys: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.fontNames = this.arguments.getStringArray(FONT_NAMES_KEY)
        this.fontKeys = this.arguments.getStringArray(FONT_KEYS_KEY)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(this.activity)
                .setItems(this.fontNames) { dialog, which ->
                    dialog.dismiss()
                    this@SelectFontDialogFragment.updateFont(which)
                }
                .create()
    }

    private fun updateFont(position: Int) {
        val activity = this.activity as GeneratorActivity
        activity.fontName = this.fontNames[position]
        activity.fontKey = this.fontKeys[position]
    }
}