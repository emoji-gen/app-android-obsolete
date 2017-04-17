package moe.pine.emoji.lib.slack

import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jsoup.Jsoup
import java.io.InputStream
import java.io.OutputStream

/**
 * RegisterClient
 * Created by pine on Apr 16, 2017.
 */
class RegisterClient {
    private val httpClient: HttpClient by lazy { HttpClient() }

    fun loadCookie(stream: InputStream) = this.httpClient.loadCookies(stream)
    fun saveCookie(stream: OutputStream) = this.httpClient.saveCookies(stream)

    fun register(
            team: String,
            email: String,
            password: String,
            emojiName: String,
            emojiUrl: String
    ): MessageResult {
        // Check login status
        val initialResponse = this.httpClient.doGetCustomizeEmoji(team)
        var body = initialResponse.body().string()
        val initialIsLoggedIn = !HtmlParser.hasSigninForm(body)

        // Login if not logged in
        if (!initialIsLoggedIn) {
            val loginFormData = HtmlParser.parseSigninFormData(body)
            loginFormData ?: return MessageResult(false, "Login failed")
            val loginFormBody = FormBody.Builder().also { builder ->
                loginFormData?.forEach {
                    when (it.key()) {
                        "email" -> builder.add(it.key(), email)
                        "password" -> builder.add(it.key(), password)
                        else -> builder.add(it.key(), it.value())
                    }
                }
            }.build()

            val response = this.httpClient.doPostSignin(team, loginFormBody)
            body = response.body().string()
            val isLoggedIn = !HtmlParser.hasSigninForm(body)
            if (!isLoggedIn) return MessageResult(false, "Login failed")
        }

        // Fetch emoji image data
        val emojiResponse = this.httpClient.doGetEmojiImage(emojiUrl)
        val emojiBytes = emojiResponse.body().bytes()

        // Send image data
        val registerFormData = HtmlParser.parseRegisterFormData(body)
        registerFormData ?: return MessageResult(false, "Login failed")
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

        val registerResponse = this.httpClient.doPostCustomizeEmoji(team, registerFormBody)
        return HtmlParser.parseAlertMessage(registerResponse.body().string())
    }
}