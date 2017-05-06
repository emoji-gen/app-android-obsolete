package moe.pine.emoji.view.common

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.crashlytics.android.Crashlytics
import moe.pine.emoji.BuildConfig
import moe.pine.emoji.model.internal.WebViewPage

/**
 * WebView
 * Created by pine on Apr 21, 2017.
 */
@SuppressWarnings("JavaScriptEnabled")
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

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (view == null || request == null) return false
                return this.shouldOverrideUrlLoadingImpl(view, request.url.toString())
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (view == null || url == null) return false
                return this.shouldOverrideUrlLoadingImpl(view, url)
            }

            private fun shouldOverrideUrlLoadingImpl(view: WebView, url: String): Boolean {
                // Twitter
                if (url.startsWith("twitter://")) {
                    val twitterRegexp = "^twitter://user\\?screen_name=(.+)$".toRegex()
                    if (twitterRegexp.matches(url)) {
                        val screenName = twitterRegexp.find(url)?.groupValues?.lastOrNull()
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=$screenName"))
                            this@WebView.context.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/$screenName"))
                                this@WebView.context.startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                if (BuildConfig.DEBUG) e.printStackTrace()
                                Crashlytics.logException(e)
                            }
                        }
                    }
                    return true
                }

                // External Links
                val isInInternal = WebViewPage.urls.any { url.startsWith(it) }
                if (!isInInternal) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        this@WebView.context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        if (BuildConfig.DEBUG) e.printStackTrace()
                        Crashlytics.logException(e)
                    }
                    return true
                }

                return false
            }
        }
    }

    init {
        this.isVerticalScrollBarEnabled = true
        this.settings.let { settings ->
            settings.displayZoomControls = false
            settings.builtInZoomControls = false
            settings.setSupportZoom(false)
        }
        this.setWebViewClient(this.client)
    }
}
