package moe.pine.emoji.lib.slack

import moe.pine.bottler.CookieStoreUtils
import okhttp3.*
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement
import java.io.InputStream
import java.io.OutputStream
import java.net.CookieManager
import java.net.CookiePolicy

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
        // Check login status
        val initialResponse = this.doGetCustomizeEmoji(team)
        var body = initialResponse.body().string()
        val initialIsLoggedIn = !this.hasSigninForm(body)

        // Login if not logged in
        if (!initialIsLoggedIn) {
            val loginFormData = this.getSigninFormData(body)
            loginFormData ?: return RegisterResult(false, "Login failed")
            val loginFormBody = FormBody.Builder().also { builder ->
                loginFormData?.forEach {
                    when (it.key()) {
                        "email" -> builder.add(it.key(), email)
                        "password" -> builder.add(it.key(), password)
                        else -> builder.add(it.key(), it.value())
                    }
                }
            }.build()

            val response = this.doPostSignin(team, loginFormBody)
            body = response.body().string()
            val isLoggedIn = !this.hasSigninForm(body)
            if (!isLoggedIn) return RegisterResult(false, "Login failed")
        }

        // Fetch emoji image data
        val emojiResponse = this.doGetEmojiImage(emojiUrl)
        val emojiBytes = emojiResponse.body().bytes()

        // Send image data
        val registerFormData = this.getRegisterFormData(body)
        registerFormData ?: return RegisterResult(false, "Login failed")
        val registerFormBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .also { builder ->
                    registerFormData?.forEach {
                        when (it.key()) {
                            "name" -> builder.addFormDataPart(it.key(), emojiName)
                            "img" -> builder.addFormDataPart(it.key(), "emoji.png",
                                    RequestBody.create(MediaType.parse("image/png"), emojiBytes))
                            else -> builder.addFormDataPart(it.key(), it.value())
                        }
                    }
                }.build()

        val registerResponse = this.doPostCustomizeEmoji(team, registerFormBody)
        return this.getRegisterResult(registerResponse.body().string())
    }

    internal fun doGetEmojiImage(emojiUrl: String): Response {
        val request = Request.Builder()
                .url(emojiUrl)
                .build()
        return this.client.newCall(request).execute()
    }

    internal fun doGetCustomizeEmoji(team: String): Response {
        val request = Request.Builder()
                .url("https://$team.slack.com/customize/emoji")
                .build()
        return this.client.newCall(request).execute()
    }

    internal fun doPostCustomizeEmoji(team: String, body: RequestBody): Response {
        val request = Request.Builder()
                .url("https://$team.slack.com/customize/emoji")
                .post(body)
                .build()
        return this.client.newCall(request).execute()
    }

    internal fun doPostSignin(team: String, formBody: FormBody): Response {
        val request = Request.Builder()
                .url("https://$team.slack.com/")
                .post(formBody)
                .build()
        return this.client.newCall(request).execute()
    }

    internal fun getSigninFormData(body: String): List<Connection.KeyVal>? {
        val doc = Jsoup.parse(body)
        val form = doc.getElementById("signin_form") as? FormElement
        return form?.formData()
    }

    internal fun getRegisterFormData(body: String): List<Connection.KeyVal>? {
        val doc = Jsoup.parse(body)
        val form = doc.getElementById("addemoji") as? FormElement
        return form?.formData()
    }

    internal fun hasSigninForm(body: String): Boolean {
        val doc = Jsoup.parse(body)
        return doc.getElementById("signin_form") is FormElement
    }

    internal fun getRegisterResult(body: String): RegisterResult {
        val doc = Jsoup.parse(body)
        val elem = doc.select(".alert:first-of-type").first()
        if (elem.hasClass("alert_success")) {
            val messsage = elem.select("strong").first().text().trim()
            return RegisterResult(true, if (messsage.isBlank()) "Successful" else messsage)
        } else {
            val message = elem.text().trim()
            return RegisterResult(false, if (message.isBlank()) "Unknown Error" else message)
        }
    }
}