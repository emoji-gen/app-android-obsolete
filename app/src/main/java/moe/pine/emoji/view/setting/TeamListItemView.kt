package moe.pine.emoji.view.setting

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_setting_team_list_item.view.*
import moe.pine.emoji.model.internal.SlackTeam

/**
 * Created by pine on May 14, 2017.
 */
class TeamListItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var team: SlackTeam? = null
        set(value) {
            this.text_view_setting_team_domain.text = value?.domain.orEmpty()
            this.text_view_setting_team_email.text = value?.email.orEmpty()
        }
}