package moe.pine.emoji.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_webview.*
import moe.pine.emoji.R
import moe.pine.emoji.components.ActionBarBackButtonComponent
import moe.pine.emoji.components.SupportActionBarComponent
import moe.pine.emoji.model.value.WebViewPage

/**
 * Activity for WebView
 * Created by pine on Apr 21, 2017.
 */
class WebViewActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context, page: WebViewPage): Intent {
            return Intent(context, WebViewActivity::class.java).also { intent ->
                intent.putExtra("page", page.ordinal)
            }
        }
    }

    val actionBar by lazy { SupportActionBarComponent(this) }
    val backButton by lazy { ActionBarBackButtonComponent(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_webview)
        this.actionBar.onCreate()

        val page = WebViewPage.of(this.intent.extras["page"] as Int)!!
        this.title = page.title
        web_view.loadUrl(page.url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return this.backButton.onOptionsItemSelected(item) or super.onOptionsItemSelected(item)
    }
}