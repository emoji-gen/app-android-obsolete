package moe.pine.emoji.lib.slack

import okhttp3.Response
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement

/**
 * Created by pine on 4/5/17.
 */

internal class HtmlParser {
    fun hasSigninForm(response: Response): Boolean {
        val body = response.body().string()
        val doc = Jsoup.parse(body)
        return doc.getElementById("signin_form") is FormElement
    }

    fun getSigninFormData(response: Response): List<Connection.KeyVal>? {
        val body = response.body().string()
        val doc = Jsoup.parse(body)
        val form = doc.getElementById("signin_form") as? FormElement
        return form?.formData()
    }
}