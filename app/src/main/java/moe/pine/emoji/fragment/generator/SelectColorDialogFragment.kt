package moe.pine.emoji.fragment.generator

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import moe.pine.emoji.R
import moe.pine.emoji.activity.GeneratorActivity
import moe.pine.emoji.activity.binding.backgroundColor
import moe.pine.emoji.activity.binding.textColor


/**
 * Fragment for color selection dialog
 * Created by pine on May 6, 2017.
 */
class SelectColorDialogFragment : DialogFragment() {
    companion object {
        val COLOR_KEY = "color"
        val IS_BACKGROUND_KEY = "isBackground"

        fun newInstance(color: Int, isBackground: Boolean = false): SelectColorDialogFragment {
            Log.d("Emoji", "color=" + color)
            return SelectColorDialogFragment().also { fragment ->
                val arguments = Bundle()
                arguments.putInt(COLOR_KEY, color)
                arguments.putBoolean(IS_BACKGROUND_KEY, isBackground)
                fragment.arguments = arguments
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val color = this.arguments.getInt(COLOR_KEY)
        Log.d("Emoji", "color=" + color)

        return ColorPickerDialogBuilder
                .with(this.context)
                .initialColor(color)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setPositiveButton(R.string.ok) { dialog, color, allColors ->
                    dialog.dismiss()
                    this@SelectColorDialogFragment.updateColor(color)
                }
                .setNegativeButton(R.string.cancel) { dialog, which -> dialog.dismiss() }
                .build()
    }

    private fun updateColor(color: Int) {
        val isBackground = this.arguments.getBoolean(IS_BACKGROUND_KEY)
        val activity = this.activity as GeneratorActivity

        if (isBackground) {
            activity.backgroundColor = color
        } else {
            activity.textColor = color
        }
    }
}