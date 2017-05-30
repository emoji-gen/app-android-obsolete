package moe.pine.emoji.adapter.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.pine.emoji.R

/**
 * Created by pine on 2017/05/30.
 */

class HistoryRecyclerAdapter(
        val context: Context
) : RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_main_history_recycler_item, parent, false)
        return ViewHolder(view)
    }
}