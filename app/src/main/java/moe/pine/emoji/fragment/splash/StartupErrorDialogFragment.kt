package moe.pine.emoji.fragment.splash

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import moe.pine.emoji.R

/**
 * Dialog for startup errors
 * Created by pine on May 7, 2017.
 */
class StartupErrorDialogFragment : DialogFragment() {
    companion object {
        fun newInstance() = StartupErrorDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(this.context)
                .setTitle(R.string.splash_startup_error_title)
                .setMessage(R.string.splash_startup_error_message)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()

                    val activity = this.context as Activity
                    activity.finish()
                }
                .create()
    }
}