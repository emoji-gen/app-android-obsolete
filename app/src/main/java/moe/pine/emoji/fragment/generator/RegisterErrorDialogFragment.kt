package moe.pine.emoji.fragment.generator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import moe.pine.emoji.R

/**
 * Fragment for register error dialog
 * Created by pine on May 14, 2017.
 */
class RegisterErrorDialogFragment : DialogFragment() {
    companion object {
        private val ERROR_MESSAGE_KEY = "errorMessage"

        fun newInstance(errorMessage: String): RegisterErrorDialogFragment {
            val fragment = RegisterErrorDialogFragment()
            val arguments = Bundle()
            arguments.putString(ERROR_MESSAGE_KEY, errorMessage)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val errorMessage = this.arguments!!.getString(ERROR_MESSAGE_KEY)

        return AlertDialog.Builder(this.context)
                .setTitle(R.string.generator_register_failed_title)
                .setMessage(errorMessage)
                .setPositiveButton(R.string.ok) { _, _ ->
                    this.dismiss()
                }
                .setCancelable(true)
                .create()
    }
}