package moe.pine.emoji.fragment.setting

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import moe.pine.emoji.R
import moe.pine.emoji.util.setMessage

/**
 * Fragment for auth progress
 * Created by pine on May 14, 2017.
 */

class AuthProgressDialogFragment : DialogFragment() {
    companion object {
        fun newInstance() = AuthProgressDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return ProgressDialog(this.context, R.style.AppTheme_ProgressDialog).also { dialog ->
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog.isIndeterminate = true
            dialog.setTitle(R.string.setting_auth_progress_title)
            dialog.setMessage(R.string.setting_auth_progress_message)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
        }
    }
}