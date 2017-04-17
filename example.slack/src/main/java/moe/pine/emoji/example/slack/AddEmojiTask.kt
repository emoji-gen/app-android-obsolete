package moe.pine.emoji.example.slack

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import moe.pine.emoji.lib.slack.MessageResult
import moe.pine.emoji.lib.slack.RegisterClient
import java.io.FileNotFoundException
import java.io.IOException

/**
 * AddEmojiTask
 * Created by pine on Apr 17, 2017.
 */
class AddEmojiTask(
        val context: Context
) : AsyncTask<AddEmojiTask.Arguments, Unit, RegisterResult>() {
    data class Arguments(
            val team: String,
            val username: String,
            val password: String,
            val emojiName: String,
            val emojiUrl: String
    )

    lateinit var dialog: ProgressDialog

    override fun onPreExecute() {
        super.onPreExecute()
        this.dialog = ProgressDialog(this.context).also {
            it.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            it.isIndeterminate = true
            it.setTitle("Loading ...")
            it.setMessage("Try to register")
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.show()
        }
    }

    override fun doInBackground(vararg params: Arguments): RegisterResult {
        val param = params[0]

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
                param.team,
                param.username,
                param.password,
                param.emojiName,
                param.emojiUrl
        )

        try {
            val ostream = this.context.openFileOutput("cookie.dat", Context.MODE_PRIVATE)
            ostream.use { client.saveCookie(it) }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result;
    }

    override fun onPostExecute(result: MessageResult) {
        super.onPostExecute(result)
        this.dialog.dismiss()

        if (result.ok) {
            AlertDialog.Builder(this.context)
                    .setTitle("Successful")
                    .setMessage(result.message ?: "Successful")
                    .setCancelable(false)
                    .setPositiveButton("Close") { dialog, _ -> dialog.dismiss() }
                    .show()
        } else {
            AlertDialog.Builder(this.context)
                    .setTitle("Failure")
                    .setMessage(result.message ?: "Error")
                    .setCancelable(false)
                    .setPositiveButton("Close") { dialog, _ -> dialog.dismiss() }
                    .show()
        }
    }
}