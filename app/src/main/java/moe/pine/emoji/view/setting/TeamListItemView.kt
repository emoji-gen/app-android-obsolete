package moe.pine.emoji.view.setting

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_setting_team_list_item.view.*
import moe.pine.emoji.fragment.setting.DeleteTeamDialogFragment
import moe.pine.emoji.model.realm.SlackTeam

/**
 * View for team list item
 * Created by pine on May 14, 2017.
 */
class TeamListItemView : RelativeLayout {
    var team: SlackTeam? = null
        set(value) {
            this.text_view_setting_team_domain.text = value?.domain.orEmpty()
            this.text_view_setting_team_email.text = value?.email.orEmpty()
            field = value
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.button_setting_team_delete.setOnClickListener { this.remove() }
    }

    private fun remove() {
        val domain = this.team?.domain ?: return
        val activity = this.context as? AppCompatActivity
        val dialog = DeleteTeamDialogFragment.newInstance(domain)
        activity?.supportFragmentManager?.let { dialog.show(it, null) }
    }
}