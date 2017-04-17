package moe.pine.emoji.lib.slack

import okhttp3.Response
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement

/**
 * HtmlParser
 * Created by pine on Apr 17, 2017.
 */
internal object HtmlParser {
    fun parseSigninFormData(body: String): List<Connection.KeyVal>? {
        val doc = Jsoup.parse(body)
        val form = doc.getElementById("signin_form") as? FormElement
        return form?.formData()
    }
}