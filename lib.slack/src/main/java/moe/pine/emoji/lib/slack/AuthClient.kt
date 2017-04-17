package moe.pine.emoji.lib.slack

import okhttp3.FormBody
import okhttp3.Response
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement

/**
 * AuthClient
 * Created by pine on Apr 16, 2017.
 */
class AuthClient {
    private val httpClient: HttpClient by lazy { HttpClient() }

    fun auth(team: String, email: String, password: String): Boolean {
        this.httpClient.clearCookies()

        val initialResponse = this.httpClient.doGetCustomizeEmoji(team)
        val formData = this.getSigninFormData(initialResponse)
        formData ?: return false

        val formBody = FormBody.Builder().also { builder ->
            formData.forEach {
                when (it.key()) {
                    "email" -> builder.add(it.key(), email)
                    "password" -> builder.add(it.key(), password)
                    else -> builder.add(it.key(), it.value())
                }
            }
        }.build()
        val response = this.httpClient.doPostSignin(team, formBody)
        val hasSigninForm = this.hasSigninForm(response)

        return !hasSigninForm
    }

    internal fun getSigninFormData(response: Response): List<Connection.KeyVal>? {
        val body = response.body().string()
        val doc = Jsoup.parse(body)
        val form = doc.getElementById("signin_form") as? FormElement
        return form?.formData()
    }

    internal fun hasSigninForm(response: Response): Boolean {
        val body = response.body().string()
        val doc = Jsoup.parse(body)
        return doc.getElementById("signin_form") is FormElement
    }
}