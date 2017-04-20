package moe.pine.emoji.view.main

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import moe.pine.emoji.activity.GeneratorActivity


/**
 * GenerateFloatingActionButton
 * Created by pine on Apr 20, 2017.
 */
class GenerateFloatingActionButton : FloatingActionButton {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.setOnClickListener {
            this.context.startActivity(GeneratorActivity.createIntent(this.context))
        }
    }
}