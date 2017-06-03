package moe.pine.emoji.view.generator

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_generator_team_spinner_item.view.*
import moe.pine.emoji.model.realm.SlackTeam

/**
 * View for team spinner
 * Created by pine on May 14, 2017.
 */
class TeamSpinnerItemView : RelativeLayout {
    var team: SlackTeam? = null
        set(value) {
            this.text_view_generator_team_name.text = value?.team.orEmpty()
            field = value
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}