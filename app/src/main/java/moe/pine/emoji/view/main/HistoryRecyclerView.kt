package moe.pine.emoji.view.main

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import moe.pine.emoji.R

/**
 * RecyclerView for emoji history
 * Created by pine on 2017/05/30.
 */
class HistoryRecyclerView : RecyclerView {
    private val itemWidth: Int by lazy {
        val inflater = LayoutInflater.from(this.context)
        val view = inflater.inflate(R.layout.view_main_history_recycler_item, null, false)
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        view.measuredWidth
    }

    init {
        this.layoutManager = GridLayoutManager(this.context, 1)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)

        val width = MeasureSpec.getSize(widthSpec)
        if (width != 0) {
            val spanCount = width / this.itemWidth
            if (spanCount > 0) {
                val layoutManager = this.layoutManager as? GridLayoutManager
                layoutManager?.spanCount = spanCount
            }
        }
    }
}