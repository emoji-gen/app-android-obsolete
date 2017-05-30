package moe.pine.emoji.lib.emoji

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import moe.pine.emoji.lib.emoji.model.Font
import moe.pine.emoji.lib.emoji.model.History

/**
 * Json deserializer
 * Created by pine on May 7, 2017.
 */
object JsonDeserializer {
    private val gson: Gson by lazy { Gson() }

    fun fontsFromJson(str: String): List<Font> =
            this.gson.fromJson(str, object : TypeToken<List<Font>>() {}.type)

    fun historiesFromJson(str: String): List<History> =
            this.gson.fromJson(str, object : TypeToken<List<History>>() {}.type)
}