package moe.pine.emoji.fragment.generator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import moe.pine.emoji.R


/**
 * Fragment for font selection dialog
 * Created by pine on May 6, 2017.
 */

class SelectFontDialogFragment : DialogFragment() {
    companion object {
        val FONT_LIST_KEY = "fontList"

        fun newInstance(fonts: List<String>): SelectFontDialogFragment {
            return SelectFontDialogFragment().also { fragment ->
                val arguments = Bundle()
                arguments.putStringArray(FONT_LIST_KEY, fonts.toTypedArray())
                fragment.arguments = arguments
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val items = this.arguments.getStringArray(FONT_LIST_KEY)
        return AlertDialog.Builder(this.activity)
                //.setTitle(R.string.generator_select_font_dialog_title)
                .setItems(items) { dialog, which ->
                    // item_which pressed
                }
                .create()
    }
}