package moe.pine.emoji.lib.slack

import okhttp3.FormBody

/**
 * AuthClient
 * Created by pine on Apr 16, 2017.
 */
class AuthClient {
    private val httpClient: HttpClient by lazy { HttpClient() }

    fun auth(team: String, email: String, password: String): Boolean {
        this.httpClient.clearCookies()

        val formData = run {
            val response = this.httpClient.doGetCustomizeEmoji(team)
            val body = response.body().string()
            HtmlParser.parseSigninFormData(body) ?: return false
        }

        val formBody = FormBody.Builder().also { builder ->
            formData.forEach {
                when (it.key()) {
                    "email" -> builder.add(it.key(), email)
                    "password" -> builder.add(it.key(), password)
                    else -> builder.add(it.key(), it.value())
                }
            }
        }.build()

        return run {
            val response = this.httpClient.doPostSignin(team, formBody)
            val body = response.body().string()
            HtmlParser.hasSigninForm(body)
        }
    }
}