package moe.pine.emoji.util.rgba

/**
 * Extensions for RgbaColorUtils
 * Created by pine on May 7, 2017.
 */

fun String.toColor() = RgbaColorUtils.toColor(this)

fun Int.toRgba() = RgbaColorUtils.toRgba(this)
