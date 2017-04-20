package moe.pine.emoji.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

/**
 * Activity for splash
 * Created by pine on Apr 18, 2017.
 */
class SplashActivity : AppCompatActivity() {
    private val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(moe.pine.emoji.R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        this.handler.postDelayed({
            this.startActivity(MainActivity.createIntent(this))
            this.finish()
        }, 700)
    }
}
