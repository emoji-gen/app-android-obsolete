package moe.pine.emoji.fragment.webview

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_webview_lazy.*
import moe.pine.emoji.R

/**
 * Fragment for lazy loaded web view
 * Created by pine on 2017/06/06.
 */
class WebViewLazyFragment : Fragment() {
    companion object {
        private val URL_KEY = "url"

        fun newInstance(uri: String): WebViewLazyFragment {
            val fragment = WebViewLazyFragment()
            val arguments = Bundle()
            arguments.putString(URL_KEY, uri)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_webview_lazy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val url = this.arguments.getString(URL_KEY)
        this.web_view.loadUrl(url)
        this.web_view.onPageFinishedListener = { this.onPageFinishedListener() }
    }

    private fun onPageFinishedListener() {
        val showAnimator = ObjectAnimator.ofFloat(this.web_view, View.ALPHA, 0f, 1f)
        this.web_view.visibility = View.VISIBLE
        showAnimator.duration = 800
        showAnimator.start()
    }
}