package moe.pine.emoji.lib.slack

import okhttp3.CookieJar
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy

/**
 * Created by pine on 4/4/17.
 */

class SlackClient {
    companion object {
        const val USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"
    }

    private val cookieJar: CookieJar by lazy {
        val cookieManager = CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER)
        CookieHandler.setDefault(cookieManager)
        JavaNetCookieJar(cookieManager)
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
                .cookieJar(this.cookieJar)
                .addNetworkInterceptor(UserAgentInterceptor(USER_AGENT))
                .build()
    }

    fun auth(team: String, email: String, password: String): Boolean {
        return true
    }
}
