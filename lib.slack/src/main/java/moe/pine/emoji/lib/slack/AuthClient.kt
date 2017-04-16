package moe.pine.emoji.lib.slack

import okhttp3.*
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement
import java.net.CookieManager
import java.net.CookiePolicy

/**
 * AuthClient
 * Created by pine on Apr 16, 2017.
 */
class AuthClient {
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
                .addNetworkInterceptor(UserAgentInterceptor(HTTP_USER_AGENT))
                .build()
    }

    fun auth(team: String, email: String, password: String): Boolean {
        this.cookieManager.cookieStore.removeAll()
        val initialResponse = this.doGetCustomizeEmoji(team)
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
        val response = this.doPostSignin(team, formBody)
        val hasSigninForm = this.hasSigninForm(response)

        return !hasSigninForm
    }

    internal fun doGetCustomizeEmoji(team: String): Response {
        val request = Request.Builder()
                .url("https://$team.slack.com/customize/emoji")
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