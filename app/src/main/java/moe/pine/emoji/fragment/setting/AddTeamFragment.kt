package moe.pine.emoji.fragment.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_setting_add_team.*
import moe.pine.emoji.R
import moe.pine.emoji.components.common.SoftInputManagerComponent
import moe.pine.emoji.components.setting.InputTextWatcherComponent
import moe.pine.emoji.components.setting.SettingSaverComponent
import moe.pine.emoji.components.setting.TeamInputClearComponent

/**
 * Fragment for add slack team
 * Created by pine on May 14, 2017.
 */

class AddTeamFragment : Fragment() {
    companion object {
        private val IS_FOCUS_KEY = "isFocus"

        fun newInstance(isFocus: Boolean = false): AddTeamFragment {
            val fragment = AddTeamFragment()
            val arguments = Bundle()
            arguments.putBoolean(IS_FOCUS_KEY, isFocus)
            fragment.arguments = arguments
            return fragment
        }
    }

    private val inputTextWatcher by lazy { InputTextWatcherComponent(this) }
    private val softInputManager by lazy { SoftInputManagerComponent(this.fragment_setting_add_team) }
    private val settingSaver by lazy { SettingSaverComponent(this) }
    private val inputClear by lazy { TeamInputClearComponent(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting_add_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.inputTextWatcher.onActivityCreated(savedInstanceState)
        this.softInputManager.onActivityCreated(savedInstanceState)
        this.settingSaver.onActivityCreated(savedInstanceState)
        this.inputClear.onActivityCreated(savedInstanceState)

        val isFocus = this.arguments!!.getBoolean(IS_FOCUS_KEY, false)
        if (isFocus) this.edit_text_setting_team.requestFocus()
    }

    override fun onDestroyView() {
        this.inputClear.onDestroyView()
        super.onDestroyView()
    }
}