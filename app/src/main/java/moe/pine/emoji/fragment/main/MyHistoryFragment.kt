package moe.pine.emoji.fragment.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.fragment_main_my_history.*
import moe.pine.emoji.R
import moe.pine.emoji.adapter.main.HistoryRecyclerAdapter
import moe.pine.emoji.model.realm.History


/**
 * Fragment for MyHistory
 * Created by pine on 2017/06/01.
 */
class MyHistoryFragment : Fragment(), RealmChangeListener<RealmResults<History>> {
    companion object {
        fun newInstance() = MyHistoryFragment()
    }

    lateinit var realm: Realm

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.realm = Realm.getDefaultInstance()
        return inflater.inflate(R.layout.fragment_main_my_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.activity.setTitle(R.string.main_my_history_title)

        val adapter = HistoryRecyclerAdapter(this.context)
        this.recycler_view.adapter = adapter

        val result = realm.where(History::class.java)
                .findAllSortedAsync("createdAt", Sort.DESCENDING)
        result.addChangeListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.realm.close()
    }

    override fun onChange(element: RealmResults<History>) {
        val recyclerView: RecyclerView? = this.recycler_view
        recyclerView ?: return

        val textViewEmpty: TextView? = this.text_view_my_history_empty
        textViewEmpty ?: return

        val histories = element.toList()
        if (histories.isEmpty()) {
            recyclerView.visibility = View.GONE
            textViewEmpty.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            textViewEmpty.visibility = View.GONE

            val adapter = recyclerView.adapter as HistoryRecyclerAdapter
            adapter.histories = histories.map { it.emojiUri }
        }
    }
}