package moe.pine.emoji.activity

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_webview.*
import moe.pine.emoji.R
import moe.pine.emoji.components.common.ActionBarBackButtonComponent
import moe.pine.emoji.components.common.SupportActionBarComponent
import moe.pine.emoji.model.value.WebViewPage
import android.view.View.GONE
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator


/**
 * Activity for WebView
 * Created by pine on Apr 21, 2017.
 */
class WebViewActivity : AppCompatActivity() {
    companion object {
        private val PAGE_KEY = "page"
        fun createIntent(context: Context, page: WebViewPage): Intent {
            return Intent(context, WebViewActivity::class.java).also { intent ->
                intent.putExtra(PAGE_KEY, page.ordinal)
            }
        }
    }

    private val actionBar by lazy { SupportActionBarComponent(this) }
    private val backButton by lazy { ActionBarBackButtonComponent(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_webview)
        this.actionBar.onCreate()

        val page = WebViewPage.of(this.intent.extras[PAGE_KEY] as Int)!!
        this.title = page.title
        this.web_view.loadUrl(page.url)
        this.web_view.onPageFinishedListener = { this.onPageFinishedListener() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return this.backButton.onOptionsItemSelected(item) or super.onOptionsItemSelected(item)
    }

    private fun onPageFinishedListener() {
        val hideAnimator = ObjectAnimator.ofFloat(this.progress_bar, View.ALPHA, 1f, 0f)
        hideAnimator.duration = 300
        hideAnimator.start()
        hideAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                this@WebViewActivity.progress_bar.visibility = View.INVISIBLE
            }
        })

        val showAnimator = ObjectAnimator.ofFloat(this.web_view, View.ALPHA, 0f, 1f)
        this.web_view.visibility = View.VISIBLE
        showAnimator.duration = 800
        showAnimator.start()
    }
}