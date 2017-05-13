package moe.pine.emoji.util.rgba

import android.graphics.Color

/**
 * Utilities for RGBA color
 * Created by pine on May 7, 2017.
 */

object RgbaColorUtils {
    fun toColor(rgba: String): Int {
        val pos = if (rgba.first() == '#') 1 else 0
        val red = rgba.slice(pos..pos + 1).toInt(16)
        val green = rgba.slice(pos + 2..pos + 3).toInt(16)
        val blue = rgba.slice(pos + 4..pos + 5).toInt(16)
        val alpha = rgba.slice(pos + 6..pos + 7).toInt(16)
        return Color.argb(alpha, red, green, blue)
    }

    fun toRgba(color: Int): String {
        val alpha = (color ushr 24) and 0xff
        val red = (color ushr 16) and 0xff
        val green = (color ushr 8) and 0xff
        val blue = color and 0xff
        return "%02X%02X%02X%02X".format(red, green, blue, alpha)
    }
}