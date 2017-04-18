package moe.pine.emoji

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


/**
 * MainApplication
 * Created by pine on Apr 18, 2017.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
    }
}
