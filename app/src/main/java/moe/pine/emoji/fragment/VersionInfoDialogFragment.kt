package moe.pine.emoji.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import moe.pine.emoji.R

/**
 * Created by pine on Apr 21, 2017.
 */
class VersionInfoDialogFragment : DialogFragment() {
    companion object {
        fun newInstance() = VersionInfoDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(this.activity)
                .setView(R.layout.dialog_version_info)
                .create()
    }
}