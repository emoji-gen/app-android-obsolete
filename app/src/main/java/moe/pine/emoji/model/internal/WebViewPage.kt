package moe.pine.emoji.model.internal

/**
 * Model for WebViewPage
 * Created by pine on Apr 21, 2017.
 */
enum class WebViewPage(
        val title: String,
        val url: String
) {
    CONTACT("お問い合わせ", "twitter://user?screen_name=pine613");

    companion object {
        val urls: List<String> by lazy { WebViewPage.values().map { it.url } }

        fun of(ordinal: Int): WebViewPage? = WebViewPage.values().find { it.ordinal == ordinal }
    }
}