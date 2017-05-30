package moe.pine.emoji.lib.emoji

import com.google.gson.Gson
import moe.pine.emoji.lib.emoji.model.Font

/**
 * Json serializer
 * Created by pine on May 7, 2017.
 */
object JsonSerializer {
    private val gson: Gson by lazy { Gson() }

    fun fontsToJson(fonts: List<Font>): String = this.gson.toJson(fonts)
}