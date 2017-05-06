package moe.pine.emoji.util

import junit.framework.Assert.assertEquals
import moe.pine.emoji.util.rgba.RgbaColorUtils
import org.junit.Test

class RgbaColorUtilsTest {
    @Test
    fun colorToRgbaTest() {
        assertEquals("#FFFFFF00", RgbaColorUtils.toRgba(0xFFFFFF))
        assertEquals("#FF000000", RgbaColorUtils.toRgba(0xFF0000))
        assertEquals("#00FF0000", RgbaColorUtils.toRgba(0x00FF00))
        assertEquals("#0000FF00", RgbaColorUtils.toRgba(0x0000FF))
        assertEquals("#00000000", RgbaColorUtils.toRgba(0x000000))
        assertEquals("#FFFFFFFF", RgbaColorUtils.toRgba((0xFF shl 24) or 0xFFFFFF))
        assertEquals("#FF0000FF", RgbaColorUtils.toRgba((0xFF shl 24) or 0xFF0000))
        assertEquals("#00FF00FF", RgbaColorUtils.toRgba((0xFF shl 24) or 0x00FF00))
        assertEquals("#0000FFFF", RgbaColorUtils.toRgba((0xFF shl 24) or 0x0000FF))
        assertEquals("#000000FF", RgbaColorUtils.toRgba((0xFF shl 24) or 0x000000))
    }
}