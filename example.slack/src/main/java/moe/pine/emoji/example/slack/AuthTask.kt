package moe.pine.emoji.example.slack

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import moe.pine.emoji.lib.slack.AuthClient

/**
 * AuthTask
 * Created by pine on Apr 16, 2017.
 */
class AuthTask(
        val context: Context
) : AsyncTask<AuthTask.Arguments, Unit, Boolean>() {
    data class Arguments(val team: String, val username: String, val password: String)

    lateinit var dialog: ProgressDialog

    override fun onPreExecute() {
        super.onPreExecute()
        this.dialog = ProgressDialog(this.context).also {
            it.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            it.isIndeterminate = true
            it.setTitle("Loading ...")
            it.setMessage("Try to login")
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.show()
        }
    }

    override fun doInBackground(vararg params: Arguments): Boolean {
        val param = params[0]
        val client = AuthClient()
        return client.auth(param.team, param.username, param.password)
    }

    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        this.dialog.dismiss()

        if (result) {
            AlertDialog.Builder(this.context)
                    .setTitle("Successful")
                    .setMessage("Login succeeded")
                    .setCancelable(false)
                    .setPositiveButton("Close") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
        } else {
            AlertDialog.Builder(this.context)
                    .setTitle("Failure")
                    .setMessage("Login failed")
                    .setCancelable(false)
                    .setPositiveButton("Close") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
        }
    }
}