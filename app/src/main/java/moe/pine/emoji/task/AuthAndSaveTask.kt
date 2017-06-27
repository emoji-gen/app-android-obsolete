package moe.pine.emoji.task

import android.content.Context
import android.os.AsyncTask
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import io.realm.Realm
import moe.pine.emoji.BuildConfig
import moe.pine.emoji.R
import moe.pine.emoji.fragment.setting.AuthProgressDialogFragment
import moe.pine.emoji.lib.slack.AuthClient
import moe.pine.emoji.model.event.TeamAddedEvent
import moe.pine.emoji.model.realm.SlackTeam
import moe.pine.emoji.util.eventBus
import java.io.IOException

/**
 * Task for auth
 * Created by pine on May 14, 2017.
 */
class AuthAndSaveTask(
        val context: Context,
        val arguments: AuthAndSaveTask.Arguments
) : AsyncTask<Unit, Unit, Boolean>() {
    data class Arguments(val team: String, val email: String, val password: String)

    lateinit var dialog: AuthProgressDialogFragment

    @UiThread
    override fun onPreExecute() {
        super.onPreExecute()
        val activity = this.context as AppCompatActivity
        this.dialog = AuthProgressDialogFragment.newInstance().also { dialog ->
            dialog.show(activity.supportFragmentManager, null)
        }
    }

    override fun doInBackground(vararg params: Unit): Boolean {
        val client = AuthClient()
        try {
            return client.auth(this.arguments.team, this.arguments.email, this.arguments.password)
        } catch (e: IOException) {
            Crashlytics.logException(e)
            if (BuildConfig.DEBUG) e.printStackTrace()
        }
        return false
    }

    @UiThread
    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        this.dialog.dismiss()

        if (result) {
            Realm.getDefaultInstance().use { realm ->
                val slackTeam = SlackTeam(
                        team = this.arguments.team,
                        email = this.arguments.email,
                        password = this.arguments.password
                )
                realm.beginTransaction()
                realm.copyToRealmOrUpdate(slackTeam)
                realm.commitTransaction()
            }

            this.eventBus.post(TeamAddedEvent())
            Toast.makeText(this.context, R.string.setting_auth_succeeded_message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this.context, R.string.setting_auth_failed_message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCancelled() {
        this.dialog.dismiss()
    }
}