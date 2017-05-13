package moe.pine.emoji.lib.emoji

import moe.pine.emoji.lib.emoji.model.Font
import okhttp3.*
import java.io.IOException

/**
 * API Client for Emoji Generator
 * Created by pine on May 7, 2017.
 */
class ApiClient {
    companion object {
        val BASE_URL = "https://emoji.pine.moe/api/v1/"
        val FONTS_URL = BASE_URL + "fonts"
    }

    private val client: OkHttpClient by lazy { OkHttpClient() }

    fun fetchFonts(callback: ApiCallback<List<Font>>) {
        val request = Request.Builder().url(FONTS_URL).build()
        this.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) = callback.onFailure(e)
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string() ?: "[]"
                val fonts = JsonDeserializer.fontsFromJson(body)
                callback.onResponse(fonts)
            }
        })
    }
}