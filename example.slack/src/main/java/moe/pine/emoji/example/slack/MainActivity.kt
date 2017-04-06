package moe.pine.emoji.example.slack

import android.app.Notification
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import moe.pine.emoji.lib.slack.SlackClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        object : AsyncTask<Unit, Unit, Unit>() {
            lateinit var dialog: ProgressDialog

            override fun onPreExecute() {
                dialog = ProgressDialog(this@MainActivity)
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                dialog.isIndeterminate = true
                dialog.setTitle("Loading ...")
                dialog.setCancelable(false)
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
                super.onPreExecute()
            }

            override fun doInBackground(vararg params: Unit): Unit {
                val client = SlackClient()

                val result = client.auth("prismrhythm", "pinemz@gmail.com", "")
                Log.d("Slack", result.toString())
            }

            override fun onPostExecute(result: Unit) {
                super.onPostExecute(result)
                dialog.dismiss()
            }
        }.execute()
    }
}
