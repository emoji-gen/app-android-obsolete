package moe.pine.emoji.components.setting


import android.os.Bundle
import com.squareup.otto.Subscribe
import moe.pine.emoji.fragment.setting.AddTeamFragment
import moe.pine.emoji.fragment.setting.binding.clear
import moe.pine.emoji.model.event.TeamAddedEvent
import moe.pine.emoji.util.eventBus
import moe.pine.emoji.util.hideSoftInput

/**
 * Component for team input clear
 * Created by pine on May 14, 2017.
 */
class TeamInputClearComponent(
        val fragment: AddTeamFragment
) {
    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.eventBus.register(this)
    }

    fun onDestroyView() {
        this.eventBus.unregister(this)
    }

    @Subscribe
    fun onTeamAdded(event: TeamAddedEvent) {
        this.fragment.clear()
    }
}