package moe.pine.emoji.view.main

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Decoration for history recycler view
 * Created by pine on 2017/05/31.
 */
class HistoryRecyclerDecoration : RecyclerView.ItemDecoration() {
    companion object {
        val PADDING_HORIZONTAL_DP = 12
        val PADDING_VERTICAL_DP = 12
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount
        spanCount ?: return

        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter.itemCount
        val rows = (itemCount + spanCount - 1) / spanCount
        val density = view.context.resources.displayMetrics.density

        if (position < spanCount) {
            outRect.top = (PADDING_VERTICAL_DP * density).toInt()
        }
        if (position % spanCount == 0) {
            outRect.left = (PADDING_HORIZONTAL_DP * density).toInt()
        }
        if (position % spanCount == spanCount - 1) {
            outRect.right = (PADDING_HORIZONTAL_DP * density).toInt()
        }
        if (position / spanCount == rows - 1) {
            outRect.bottom = (PADDING_VERTICAL_DP * density).toInt()
        }
    }
}