package moe.pine.emoji.fragment.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main_my_history.*
import moe.pine.emoji.R
import moe.pine.emoji.adapter.main.HistoryRecyclerAdapter
import moe.pine.emoji.lib.emoji.ApiCallback
import moe.pine.emoji.lib.emoji.ApiClient
import moe.pine.emoji.lib.emoji.model.History
import java.io.IOException

/**
 * Fragment for our history
 * Created by pine on 2017/06/01.
 */
class OurHistoryFragment : Fragment(), ApiCallback<List<History>> {
    companion object {
        fun newInstance() = OurHistoryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_our_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.activity!!.setTitle(R.string.main_our_history_title)

        val adapter = HistoryRecyclerAdapter(this.context!!)
        this.recycler_view.adapter = adapter

        val client = ApiClient()
        client.fetchHistories(callback = this)
    }

    override fun onFailure(e: IOException?) {
        super.onFailure(e)
    }

    override fun onResponse(response: List<History>) {
        super.onResponse(response)

        val recyclerView: RecyclerView? = this.recycler_view
        recyclerView ?: return

        val adapter = recyclerView.adapter as? HistoryRecyclerAdapter
        adapter?.histories = response.map { it.emojiUrl }
    }
}