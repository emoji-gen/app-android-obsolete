package moe.pine.emoji.lib.slack

import moe.pine.bottler.CookieStoreUtils
import okhttp3.*
import java.io.InputStream
import java.io.OutputStream
import java.net.CookieManager
import java.net.CookiePolicy
import java.net.CookieStore

/**
 * RegisterClient
 * Created by pine on Apr 16, 2017.
 */
class RegisterClient {
    companion object {
        const val HTTP_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"
    }

    private val cookieManager: CookieManager by lazy {
        CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER)
    }

    private val cookieJar: CookieJar by lazy {
        JavaNetCookieJar(this.cookieManager)
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
                .cookieJar(this.cookieJar)
                .addNetworkInterceptor(UserAgentInterceptor(AuthClient.HTTP_USER_AGENT))
                .build()
    }

    fun loadCookie(stream: InputStream) {
        CookieStoreUtils.readFrom(this.cookieManager.cookieStore, stream)
    }

    fun saveCookie(stream: OutputStream) {
        CookieStoreUtils.writeTo(this.cookieManager.cookieStore, stream)
    }

    fun register(
            team: String,
            email: String,
            password: String,
            emojiName: String,
            emojiUrl: String
    ): RegisterResult {

        return RegisterResult(true, "")
    }

    internal fun doGetCustomizeEmoji(team: String): Response {
        val request = Request.Builder()
                .url("https://$team.slack.com/customize/emoji")
                .build()
        return this.client.newCall(request).execute()
    }
}