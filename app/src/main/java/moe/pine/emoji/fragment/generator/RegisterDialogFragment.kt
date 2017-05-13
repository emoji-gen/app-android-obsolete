package moe.pine.emoji.fragment.generator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import moe.pine.emoji.R
import moe.pine.emoji.view.generator.InputTextDialogView
import moe.pine.emoji.view.generator.RegisterDialogView

/**
 * Fragment for register emoji
 * Created by pine on May 13, 2017.
 */
class RegisterDialogFragment : DialogFragment() {
    companion object {
        private val URI_KEY = "uri"

        fun newInstance(uri: String): RegisterDialogFragment {
            val fragment = RegisterDialogFragment()
            val arguments = Bundle()
            arguments.putString(URI_KEY, uri)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(this.context)
        val view = inflater.inflate(R.layout.dialog_register, null, false) as RegisterDialogView
        val dialog = AlertDialog.Builder(this.activity)
                .setView(view)
                .create()
        return dialog
    }
}