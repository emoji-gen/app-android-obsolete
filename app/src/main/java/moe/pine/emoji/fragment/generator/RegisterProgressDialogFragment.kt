package moe.pine.emoji.fragment.generator

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import moe.pine.emoji.R
import moe.pine.emoji.util.setMessage

/**
 * Fragment for register progress dialog
 * Created by pine on May 14, 2017.
 */
class RegisterProgressDialogFragment : DialogFragment() {
    companion object {
        fun newInstance() = RegisterProgressDialogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return ProgressDialog(this.context, R.style.AppTheme_ProgressDialog).also { dialog ->
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog.isIndeterminate = true
            dialog.setTitle(R.string.generator_register_progress_title)
            dialog.setMessage(R.string.generator_register_progress_message)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
        }
    }
}