package moe.pine.emoji.view.main

import android.content.Context
import android.content.pm.PackageInfo
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * TextView for app version info
 * Created by pine on May 14, 2017.
 */
class VersionInfoTextView : AppCompatTextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.text = this.versionName
    }

    private val versionName: String
        get() {
            val pkgInfo: PackageInfo? = this.context.packageManager?.getPackageInfo(context.packageName, 0)
            return pkgInfo?.versionName.orEmpty()
        }
}