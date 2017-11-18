package moe.pine.emoji.fragment.setting

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import moe.pine.emoji.R
import moe.pine.emoji.model.event.TeamDeleteEvent
import moe.pine.emoji.util.eventBus

/**
 * Fragment for delete team dialog
 * Created by pine on May 14, 2017.
 */
class DeleteTeamDialogFragment : DialogFragment() {
    companion object {
        private val DOMAIN_KEY = "team"

        fun newInstance(domain: String): DeleteTeamDialogFragment {
            val fragment = DeleteTeamDialogFragment()
            val arguments = Bundle()
            arguments.putString(DOMAIN_KEY, domain)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val domain = this.arguments!!.getString(DOMAIN_KEY)

        return AlertDialog.Builder(this.context, R.style.AppTheme_Dialog_Red)
                .setTitle(R.string.setting_remove_team_message_title)
                .setMessage(this.getString(R.string.setting_remove_team_message_message, domain))
                .setPositiveButton(R.string.ok) { _, _ ->
                    this.eventBus.post(TeamDeleteEvent(domain))
                }
                .setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(true)
                .create()
    }
}