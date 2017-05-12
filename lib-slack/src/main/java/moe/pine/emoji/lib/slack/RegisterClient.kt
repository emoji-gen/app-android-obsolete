package moe.pine.emoji.lib.slack

import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jsoup.Connection
import java.io.InputStream
import java.io.OutputStream

/**
 * RegisterClient
 * Created by pine on Apr 16, 2017.
 */
class RegisterClient {
    private data class InitialAccessResult(
            val isLoggedIn: Boolean,
            val signinFormData: List<Connection.KeyVal>?,
            val registerFormData: List<Connection.KeyVal>?
    )

    private data class LoginResult(
            val isLoggedIn: Boolean,
            val registerFormData: List<Connection.KeyVal>?
    )

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
        val initialResult = this.tryInitialAccess(team)
        var loginResult: LoginResult? = null

        // Login if not logged in
        if (!initialResult.isLoggedIn) {
            initialResult.signinFormData ?: return MessageResult(false, "Login failed")
            loginResult = this.tryLogin(team, email, password, initialResult.signinFormData)
            if (!loginResult.isLoggedIn) return MessageResult(false, "Login failed")
        }

        // Fetch emoji image data
        val emojiBytes = this.fetchEmojiBytes(emojiUrl)

        // Send image data
        val registerFormData = initialResult.registerFormData ?: loginResult?.registerFormData
        registerFormData ?: return MessageResult(false, "Login failed")
        return this.tryRegister(team, emojiName, emojiBytes, registerFormData)
    }

    private fun tryInitialAccess(team: String): InitialAccessResult {
        val response = this.httpClient.doGetCustomizeEmoji(team)
        val body = response.body().string()
        val isLoggedIn = !HtmlParser.hasSigninForm(body)
        val signinFormData = HtmlParser.parseSigninFormData(body)
        val registerFormData = HtmlParser.parseRegisterFormData(body)
        return InitialAccessResult(isLoggedIn, signinFormData, registerFormData)
    }

    private fun tryLogin(
            team: String,
            email: String,
            password: String,
            signinFormData: List<Connection.KeyVal>
    ): LoginResult {
        val loginFormBody = FormBody.Builder().also { builder ->
            signinFormData.forEach {
                when (it.key()) {
                    "email" -> builder.add(it.key(), email)
                    "password" -> builder.add(it.key(), password)
                    else -> builder.add(it.key(), it.value())
                }
            }
        }.build()

        val response = this.httpClient.doPostSignin(team, loginFormBody)
        val body = response.body().string()
        val isLoggedIn = !HtmlParser.hasSigninForm(body)
        val registerFormData = HtmlParser.parseRegisterFormData(body)
        return LoginResult(isLoggedIn, registerFormData)
    }

    private fun tryRegister(
            team: String,
            emojiName: String,
            emojiBytes: ByteArray,
            registerFormData: List<Connection.KeyVal>
    ): MessageResult {
        val registerFormBody = MultipartBody.Builder().also { builder ->
            builder.setType(MultipartBody.FORM)
            registerFormData.forEach {
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

    private fun fetchEmojiBytes(emojiUrl: String): ByteArray {
        val response = this.httpClient.doGetEmojiImage(emojiUrl)
        return response.body().bytes()
    }
}