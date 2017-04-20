package moe.pine.emoji.model.internal

/**
 * Model for WebViewPage
 * Created by pine on Apr 21, 2017.
 */
enum class WebViewPage(
        val title: String,
        val url: String
) {
    CONTACT("お問い合わせ", "https://www.google.co.jp");

    companion object {
        fun of(ordinal: Int): WebViewPage? = WebViewPage.values().find { it.ordinal == ordinal }
    }
}