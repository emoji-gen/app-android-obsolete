package moe.pine.emoji.view.generator

import android.content.Context
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.widget.LinearLayout
import com.squareup.otto.Subscribe
import io.realm.Realm
import io.realm.Sort
import kotlinx.android.synthetic.main.dialog_register.view.*
import moe.pine.emoji.adapter.generator.GeneratorTeamListAdapter
import moe.pine.emoji.components.generator.EmojiRegisterComponent
import moe.pine.emoji.components.generator.InputChangedWatcherComponent
import moe.pine.emoji.model.event.TeamAddedEvent
import moe.pine.emoji.model.event.TeamDeleteEvent
import moe.pine.emoji.model.realm.SlackTeam
import moe.pine.emoji.util.eventBus

/**
 * View for register dialog
 * Created by pine on May 13, 2017.
 */
class RegisterDialogView : LinearLayout {
    private lateinit var realm: Realm

    var previewUri: String = ""
    var downloadUri: String = ""
    var fragment: Fragment? = null

    private val inputWatcher by lazy { InputChangedWatcherComponent(this) }
    private val register by lazy { EmojiRegisterComponent(this) }

    private val teams: List<SlackTeam>
        get() = this.realm.where(SlackTeam::class.java).findAllSorted("team", Sort.ASCENDING)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (this.isInEditMode) return

        this.inputWatcher.onFinishInflate()
        this.register.onFinishInflate()

        this.realm = Realm.getDefaultInstance()
        this.eventBus.register(this)

        val adapter = GeneratorTeamListAdapter(this.context)
        this.spinner_generator_teams.adapter = adapter
        this.update()
    }

    override fun onDetachedFromWindow() {
        this.eventBus.unregister(this)
        this.realm.close()
        super.onDetachedFromWindow()
    }

    @Subscribe
    fun onTeamAdded(event: TeamAddedEvent) {
        this.update()
    }

    @Subscribe
    fun onTeamDeleted(event: TeamDeleteEvent) {
        this.update()
    }

    private fun update() {
        val adapter = this.spinner_generator_teams.adapter as GeneratorTeamListAdapter
        adapter.replaceAll(this.teams)
    }
}