package moe.pine.emoji.task

import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.realm.Realm
import moe.pine.emoji.R
import moe.pine.emoji.fragment.generator.RegisterErrorDialogFragment
import moe.pine.emoji.fragment.generator.RegisterProgressDialogFragment
import moe.pine.emoji.lib.slack.MessageResult
import moe.pine.emoji.lib.slack.RegisterClient
import moe.pine.emoji.model.event.EmojiRegisteredEvent
import moe.pine.emoji.model.realm.SlackTeam
import moe.pine.emoji.util.eventBus
import java.io.FileNotFoundException
import java.io.IOException

/**
 * AsyncTask for register and save emoji
 * Created by pine on May 14, 2017.
 */
class RegisterAndSaveTask(
        val context: Context,
        val arguments: RegisterAndSaveTask.Arguments
) : AsyncTask<Unit, Unit, MessageResult>() {
    data class Arguments(
            val team: String,
            val emojiName: String,
            val emojiUri: String
    )

    lateinit var dialog: DialogFragment

    override fun onPreExecute() {
        super.onPreExecute()

        val activity = this.context as AppCompatActivity
        this.dialog = RegisterProgressDialogFragment.newInstance().also { dialog ->
            dialog.show(activity.supportFragmentManager, null)
        }
    }

    override fun doInBackground(vararg params: Unit): MessageResult {
        Realm.getDefaultInstance().use { realm ->
            val team: SlackTeam? = realm.where(SlackTeam::class.java)
                    .equalTo("team", this.arguments.team)
                    .findFirst()

            team ?: return MessageResult(false, "Can\'t find team")

            val client = RegisterClient()
            try {
                val istream = this.context.openFileInput("cookie.dat")
                istream.use { client.loadCookie(it) }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val result = client.register(
                    team.team,
                    team.email,
                    team.password,
                    this.arguments.emojiName,
                    this.arguments.emojiUri
            )

            try {
                val ostream = this.context.openFileOutput("cookie.dat", Context.MODE_PRIVATE)
                ostream.use { client.saveCookie(it) }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return result
        }
    }

    override fun onPostExecute(result: MessageResult) {
        super.onPostExecute(result)
        this.dialog.dismiss()

        if (result.ok) {
            Toast.makeText(this.context, R.string.generator_register_succeeded_message, Toast.LENGTH_LONG).show()
            this.eventBus.post(EmojiRegisteredEvent())
        } else {
            val activity = this.context as AppCompatActivity
            val message = result.message ?: this.context.getString(R.string.generator_register_unknown_error)
            this.dialog = RegisterErrorDialogFragment.newInstance(message).also { dialog ->
                dialog.show(activity.supportFragmentManager, null)
            }
        }
    }
}