package moe.pine.emoji.fragment.generator

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import com.squareup.otto.Subscribe
import moe.pine.emoji.R
import moe.pine.emoji.model.event.EmojiRegisteredEvent
import moe.pine.emoji.util.eventBus
import moe.pine.emoji.view.generator.RegisterDialogView

/**
 * Fragment for register emoji
 * Created by pine on May 13, 2017.
 */
class RegisterDialogFragment : DialogFragment() {
    companion object {
        private val PREVIEW_URI_KEY = "previewUri"
        private val DOWNLOAD_URI_KEY = "downloadUri"

        fun newInstance(previewUri: String, downloadUri: String): RegisterDialogFragment {
            val fragment = RegisterDialogFragment()
            val arguments = Bundle()
            arguments.putString(PREVIEW_URI_KEY, previewUri)
            arguments.putString(DOWNLOAD_URI_KEY, downloadUri)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.eventBus.register(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(this.context)
        val view = inflater.inflate(R.layout.dialog_register, null, false) as RegisterDialogView
        view.previewUri = this.arguments.getString(PREVIEW_URI_KEY)
        view.downloadUri = this.arguments.getString(DOWNLOAD_URI_KEY)
        view.fragment = this

        val dialog = AlertDialog.Builder(this.activity)
                .setView(view)
                .create()
        return dialog
    }

    override fun onDestroyView() {
        this.eventBus.unregister(this)
        super.onDestroyView()
    }

    @Subscribe
    fun onEmojiRegistered(event: EmojiRegisteredEvent) {
        this.dismiss()
    }
}