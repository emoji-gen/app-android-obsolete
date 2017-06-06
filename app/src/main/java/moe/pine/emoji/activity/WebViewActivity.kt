package moe.pine.emoji.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import moe.pine.emoji.R
import moe.pine.emoji.components.common.ActionBarBackButtonComponent
import moe.pine.emoji.components.common.SupportActionBarComponent
import moe.pine.emoji.fragment.webview.WebViewLazyFragment
import moe.pine.emoji.model.value.WebViewPage


/**
 * Activity for WebView
 * Created by pine on Apr 21, 2017.
 */
class WebViewActivity : AppCompatActivity() {
    companion object {
        private val PAGE_KEY = "page"
        private val LAZY_MS = 10L

        fun createIntent(context: Context, page: WebViewPage): Intent {
            return Intent(context, WebViewActivity::class.java).also { intent ->
                intent.putExtra(PAGE_KEY, page.ordinal)
            }
        }
    }

    private val handler = Handler()
    private val actionBar by lazy { SupportActionBarComponent(this) }
    private val backButton by lazy { ActionBarBackButtonComponent(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_webview)
        this.actionBar.onCreate()

        val page = WebViewPage.of(this.intent.extras[PAGE_KEY] as Int)!!
        this.title = page.title

        if (savedInstanceState == null) {
            this.handler.postDelayed({
                val transaction = this.supportFragmentManager.beginTransaction()
                transaction.add(R.id.container, WebViewLazyFragment.newInstance(page.url), null)
                transaction.commitAllowingStateLoss()
            }, LAZY_MS)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return this.backButton.onOptionsItemSelected(item) or super.onOptionsItemSelected(item)
    }
}