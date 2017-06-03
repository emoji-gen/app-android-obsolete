package moe.pine.emoji.view.generator

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import moe.pine.emoji.activity.GeneratorActivity
import moe.pine.emoji.activity.binding.downloadUri
import moe.pine.emoji.fragment.generator.RegisterDialogFragment

/**
 * Button fragment for register emoji
 * Created by pine on May 13, 2017.
 */
class RegisterButtonView : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.setOnClickListener { this.register() }
    }

    private fun register() {
        val activity = this.context as GeneratorActivity
        val dialog = RegisterDialogFragment.newInstance(activity.downloadUri.toString())
        activity.supportFragmentManager?.let { dialog.show(it, null) }
    }
}