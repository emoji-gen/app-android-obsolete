package moe.pine.emoji.model.internal

/**
 * Model for WebViewPage
 * Created by pine on Apr 21, 2017.
 */
enum class WebViewPage(
        val title: String,
        val url: String
) {
    CONTACT("お問い合わせ", "http://192.168.13.34:3474/contact.html");

    companion object {
        val urls: List<String> by lazy { WebViewPage.values().map { it.url } }

        fun of(ordinal: Int): WebViewPage? = WebViewPage.values().find { it.ordinal == ordinal }
    }
}