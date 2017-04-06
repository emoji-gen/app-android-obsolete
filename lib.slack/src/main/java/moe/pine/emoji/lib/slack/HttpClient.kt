package moe.pine.emoji.lib.slack

import okhttp3.*
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy

/**
 * HTTP Client for Slack Web
 * Created by pine on 4/5/17.
 */
internal class HttpClient {
    companion object {
        const val HTTP_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"
    }

    private val cookieManager: CookieManager by lazy {
        CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER).apply {
            CookieHandler.setDefault(this)
        }
    }

    private val cookieJar: CookieJar by lazy {
        JavaNetCookieJar(this.cookieManager)
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
                .cookieJar(this.cookieJar)
                .addNetworkInterceptor(UserAgentInterceptor(HTTP_USER_AGENT))
                .build()
    }

    fun clearCookies() {
        this.cookieManager.cookieStore.removeAll()
    }

    fun doGetCustomizeEmoji(team: String): Response {
        val request = Request.Builder()
                .url("https://$team.slack.com/customize/emoji")
                .build()
        return this.client.newCall(request).execute()
    }

    fun doPostSignin(team: String, formBody: FormBody): Response {
        val request = Request.Builder()
                .url("https://$team.slack.com/")
                .post(formBody)
                .build()
        return this.client.newCall(request).execute()
    }
}