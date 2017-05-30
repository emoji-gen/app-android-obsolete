package moe.pine.emoji.lib.emoji

import android.os.Handler
import android.os.Looper
import moe.pine.emoji.lib.emoji.model.Font
import moe.pine.emoji.lib.emoji.model.History
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
        val HISTORY_URL = BASE_URL + "histories"
    }

    private val client: OkHttpClient by lazy { OkHttpClient() }
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    fun fetchFonts(callback: ApiCallback<List<Font>>) {
        val request = Request.Builder().url(FONTS_URL).build()
        this.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                this@ApiClient.handler.post { callback.onFailure(e) }
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string() ?: "[]"
                val fonts = JsonDeserializer.fontsFromJson(body)
                this@ApiClient.handler.post { callback.onResponse(fonts) }
            }
        })
    }

    fun fetchHistories(callback: ApiCallback<List<History>>) {
        val request = Request.Builder().url(HISTORY_URL).build()
        this.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                this@ApiClient.handler.post { callback.onFailure(e) }
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string() ?: "[]"
                val histories = JsonDeserializer.historiesFromJson(body)
                this@ApiClient.handler.post { callback.onResponse(histories) }
            }
        })
    }
}