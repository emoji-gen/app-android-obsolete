package moe.pine.emoji.util

import android.app.ProgressDialog
import android.support.annotation.StringRes

/**
 * Extensions for ProgressDialog
 * Created by pine on May 14, 2017.
 */

fun ProgressDialog.setMessage(@StringRes res: Int) {
    this.setMessage(this.context.getString(res))
}