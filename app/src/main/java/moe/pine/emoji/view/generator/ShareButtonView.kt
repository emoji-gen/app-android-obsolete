package moe.pine.emoji.view.generator

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ShareCompat
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Toast
import moe.pine.emoji.R
import moe.pine.emoji.activity.GeneratorActivity
import moe.pine.emoji.activity.binding.previewUri
import moe.pine.emoji.activity.binding.text
import okhttp3.*
import okio.Okio
import java.io.File
import java.io.IOException


/**
 * Share button
 * Created by pine on May 13, 2017.
 */
class ShareButtonView : LinearLayout, Callback {
    private val client by lazy { OkHttpClient() }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.setOnClickListener { this.share() }
    }

    fun onRequestPermissionResult(permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            this.share()
        } else {
            Toast.makeText(this.context, R.string.generator_share_permission_error_message, Toast.LENGTH_LONG).show()
        }
    }

    private fun share() {
        val activity = this.context as GeneratorActivity
        val request = Request.Builder().url(activity.previewUri.toString()).build()
        this.client.newCall(request).enqueue(this)
    }

    override fun onFailure(call: Call, e: IOException) {
        @StringRes val messageId = R.string.network_error_message
        this.post { Toast.makeText(this.context, messageId, Toast.LENGTH_LONG).show() }
    }
    
    override fun onResponse(call: Call, response: Response) {
        val activity = this.context as GeneratorActivity
        val permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), 0)
            return
        }

        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val fileName = activity.text.replace("\n", "_") + ".png";
        val downloadedFile = File(downloadDir, fileName)
        val sink = Okio.buffer(Okio.sink(downloadedFile))
        sink.writeAll(response.body().source())
        sink.close()

        ShareCompat.IntentBuilder.from(activity)
                .setChooserTitle(R.string.generator_share_dialog_title)
                .setType("image/png")
                .setStream(Uri.fromFile(downloadedFile))
                .startChooser()
    }
}