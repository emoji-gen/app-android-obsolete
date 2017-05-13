package moe.pine.emoji.view.generator

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import moe.pine.emoji.activity.SettingActivity

/**
 * Button for add team
 * Created by pine on May 13, 2017.
 */
class AddTeamButton : AppCompatButton {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.setOnClickListener {
            this.context.startActivity(SettingActivity.createIntent(this.context))
        }
    }
}