package moe.pine.emoji.lib.emoji

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
    private val gson: Gson by lazy { Gson() }

    fun fetchFonts(callback: ApiCallback<List<Font>>) {
        val request = Request.Builder().url(FONTS_URL).build()
        this.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) = callback.onFailure(e)
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string() ?: "{}"
                val fonts: List<Font> = gson.fromJson(body, object : TypeToken<List<Font>>() {}.type)
                callback.onResponse(fonts)
            }
        })
    }
}