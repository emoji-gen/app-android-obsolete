package moe.pine.emoji.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

/**
 * Activity for splash
 * Created by pine on Apr 18, 2017.
 */
class SplashActivity : AppCompatActivity() {
    companion object {
        val SPLASH_DELAY_MS = 700L
    }

    private val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(moe.pine.emoji.R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        this.handler.postDelayed({
            this.startActivity(MainActivity.createIntent(this))
            this.finish()
        }, SPLASH_DELAY_MS)
    }
}
