package moe.pine.emoji.lib.slack

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement

/**
 * HtmlParser
 * Created by pine on Apr 17, 2017.
 */
internal object HtmlParser {
    fun hasSigninForm(body: String): Boolean {
        val doc = Jsoup.parse(body)
        return doc.getElementById("signin_form") is FormElement
    }

    fun parseSigninFormData(body: String): List<Connection.KeyVal>? {
        val doc = Jsoup.parse(body)
        val form = doc.getElementById("signin_form") as? FormElement
        return form?.formData()
    }

    fun parseRegisterFormData(body: String): List<Connection.KeyVal>? {
        val doc = Jsoup.parse(body)
        val form = doc.getElementById("addemoji") as? FormElement
        return form?.formData()
    }

    fun parseAlertMessage(body: String): MessageResult {
        val doc = Jsoup.parse(body)
        val elem = doc.select(".alert:first-of-type").first()
        if (elem.hasClass("alert_success")) {
            val message = elem.select("strong").first().text().trim()
            return MessageResult(true, if (message.isBlank()) null else message)
        } else {
            val message = elem.text().trim()
            return MessageResult(false, if (message.isBlank()) null else message)
        }
    }
}