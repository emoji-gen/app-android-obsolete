package moe.pine.emoji.example.slack

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * AuthFragment
 * Created by pine on Apr 13, 2017.
 */
class AuthFragment : Fragment() {
    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_auth, container, false)
}