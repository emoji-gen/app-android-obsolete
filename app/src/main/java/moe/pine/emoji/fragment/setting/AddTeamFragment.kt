package moe.pine.emoji.fragment.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_setting_add_team.*
import moe.pine.emoji.R
import moe.pine.emoji.components.setting.InputTextWatcherComponent

/**
 * Fragment for add slack team
 * Created by pine on May 14, 2017.
 */

class AddTeamFragment : Fragment() {
    companion object {
        fun newInstance() = AddTeamFragment()
    }

    val inputTextWatcher by lazy { InputTextWatcherComponent(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting_add_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.inputTextWatcher.onActivityCreated(savedInstanceState)
    }
}