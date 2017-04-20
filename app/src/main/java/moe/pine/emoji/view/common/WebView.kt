package moe.pine.emoji.view.common

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * WebView
 * Created by pine on Apr 21, 2017.
 */
class WebView : WebView {
    var onPageFinishedListener: ((view: WebView, url: String) -> Unit)? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val client by lazy {
        object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                this@WebView.onPageFinishedListener?.invoke(view, url)
            }
        }
    }

    init {
        this.isVerticalScrollBarEnabled = true
        this.setWebViewClient(this.client)
        Log.d("WebView", "init")
    }
}