package moe.pine.emoji.fragment.generator

import android.app.Dialog
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v4.app.DialogFragment
import android.support.v4.app.ShareCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import moe.pine.emoji.R
import moe.pine.emoji.activity.GeneratorActivity
import moe.pine.emoji.activity.binding.text
import okhttp3.*
import okio.Okio
import java.io.File
import java.io.IOException

/**
 * Fragment for share progress dialog
 * Created by pine on May 13, 2017.
 */

class ShareProgressDialogFragment : DialogFragment(), Callback {
    companion object {
        private val URI_KEY = "uri"

        fun newInstance(uri: String): ShareProgressDialogFragment {
            val fragment = ShareProgressDialogFragment()
            val arguments = Bundle()
            arguments.putString(URI_KEY, uri)
            fragment.arguments = arguments
            return fragment
        }
    }

    private val client by lazy { OkHttpClient() }
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val uri = this.arguments.getString(URI_KEY)
        val request = Request.Builder().url(uri).build()
        this.client.newCall(request).enqueue(this)

        return ProgressDialog(this.context, R.style.AppTheme_ProgressDialog).also { dialog ->
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setTitle(R.string.generator_share_download_title)
            dialog.setMessage(this.getString(R.string.generator_share_download_message))
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        @StringRes val messageId = R.string.network_error_message
        this.handler.post { Toast.makeText(this.context, messageId, Toast.LENGTH_LONG).show() }
        this.dismiss()
    }

    override fun onResponse(call: Call, response: Response) {
        val activity = this.context as GeneratorActivity
        val permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), 0)
            this.dismiss()
            return
        }

        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val fileName = activity.text.replace("\n", "_") + ".png"
        val downloadedFile = File(downloadDir, fileName)
        val sink = Okio.buffer(Okio.sink(downloadedFile))
        sink.writeAll(response.body().source())
        sink.close()

        ShareCompat.IntentBuilder.from(activity)
                .setChooserTitle(R.string.generator_share_dialog_title)
                .setType("image/png")
                .setStream(Uri.fromFile(downloadedFile))
                .startChooser()

        this.dismiss()
    }
}