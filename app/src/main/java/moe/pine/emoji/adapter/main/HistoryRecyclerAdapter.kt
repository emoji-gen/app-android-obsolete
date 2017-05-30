package moe.pine.emoji.adapter.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_main_history_recycler_item.view.*
import moe.pine.emoji.R
import moe.pine.emoji.lib.emoji.model.History

/**
 * Created by pine on 2017/05/30.
 */

class HistoryRecyclerAdapter(
        val context: Context
) : RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>() {
    var histories: List<History> = emptyList()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.histories.getOrNull(position)

        if (item == null) {
            holder.itemView.image_view_emoji.setImageDrawable(null)
        } else {
            Glide.with(this.context)
                    .load(item.emojiUrl)
                    .fitCenter()
                    .into(holder.itemView.image_view_emoji)
        }
    }

    override fun getItemCount(): Int {
        return this.histories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_main_history_recycler_item, parent, false)
        return ViewHolder(view)
    }
}