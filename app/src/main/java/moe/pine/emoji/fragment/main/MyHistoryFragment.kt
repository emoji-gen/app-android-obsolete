package moe.pine.emoji.fragment.main

import android.os.Bundle
import android.support.v4.app.Fragment
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
 * Fragment for MyHistory
 * Created by pine on 2017/06/01.
 */
class MyHistoryFragment : Fragment() {
    companion object {
        fun newInstance() = MyHistoryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_my_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}