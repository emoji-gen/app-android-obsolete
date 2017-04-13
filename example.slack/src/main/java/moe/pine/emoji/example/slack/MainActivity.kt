package moe.pine.emoji.example.slack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        val adapter = MainPagerAdapter(this.supportFragmentManager)
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onResume() {
        super.onResume()

        /*
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
        */
    }
}
