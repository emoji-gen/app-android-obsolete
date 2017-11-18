package moe.pine.emoji.fragment.generator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import moe.pine.emoji.R
import moe.pine.emoji.activity.GeneratorActivity
import moe.pine.emoji.activity.binding.text
import moe.pine.emoji.view.generator.InputTextDialogView

/**
 * Fragment for emoji text dialog
 * Created by pine on May 6, 2017.
 */
class InputTextDialogFragment : DialogFragment() {
    companion object {
        val TEXT_KEY = "text"

        fun newInstance(): InputTextDialogFragment = this.newInstance("")
        fun newInstance(text: String): InputTextDialogFragment {
            return InputTextDialogFragment().also { fragment ->
                val arguments = Bundle()
                arguments.putString(TEXT_KEY, text)
                fragment.arguments = arguments
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(this.context)
        val view = inflater.inflate(R.layout.dialog_text_input, null, false) as InputTextDialogView
        val dialog = AlertDialog.Builder(this.activity)
                .setView(view)
                .setOnCancelListener { dialog ->
                    Log.d("Emoji", "cancel")
                    dialog.dismiss()
                    (this.activity as GeneratorActivity).text = ""
                }
                .create()

        view.onOkClickListener = {
            dialog.dismiss()
            (this.activity as GeneratorActivity).text = view.inputText
        }
        view.inputText = this.arguments!!.getString(TEXT_KEY)

        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        return dialog
    }
}