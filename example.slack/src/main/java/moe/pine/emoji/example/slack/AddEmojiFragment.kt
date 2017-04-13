package moe.pine.emoji.example.slack

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * AddEmojiFragment
 * Created by pine on Apr 13, 2017.
 */
class AddEmojiFragment : Fragment() {
    companion object {
        fun newInstance() = AddEmojiFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_add_emoji, container, false)
}