package moe.pine.emoji.lib.slack

import android.util.Log
import okhttp3.FormBody
import okhttp3.Request
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement

/**
 * Created by pine on 4/4/17.
 */

class SlackClient {
    private val httpClient: HttpClient by lazy { HttpClient() }
    private val htmlParser: HtmlParser by lazy { HtmlParser() }

    fun auth(team: String, email: String, password: String): Boolean {
        this.httpClient.clearCookies()

        val initialResponse = this.httpClient.doGetCustomizeEmoji(team)
        val formData = this.htmlParser.getSigninFormData(initialResponse)
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
        val hasSigninForm = this.htmlParser.hasSigninForm(response)

        return !hasSigninForm
    }
}
