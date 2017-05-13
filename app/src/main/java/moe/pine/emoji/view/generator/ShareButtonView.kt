package moe.pine.emoji.view.generator

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ShareCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Toast
import moe.pine.emoji.R
import moe.pine.emoji.activity.GeneratorActivity
import moe.pine.emoji.activity.binding.previewUri
import moe.pine.emoji.activity.binding.text
import moe.pine.emoji.fragment.generator.ShareProgressDialogFragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import okio.Okio
import java.io.File
import java.io.IOException


/**
 * Share button
 * Created by pine on May 13, 2017.
 */
class ShareButtonView : LinearLayout {

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
        val dialog = ShareProgressDialogFragment.newInstance(activity.previewUri.toString())
        activity.supportFragmentManager?.let { dialog.show(it, null) }
    }
}