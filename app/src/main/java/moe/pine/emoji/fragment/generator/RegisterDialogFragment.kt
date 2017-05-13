package moe.pine.emoji.fragment.generator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

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
        val dialog = AlertDialog.Builder(this.activity)
                .create()
        return dialog
    }
}