package moe.pine.emoji

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * MainApplication
 * Created by pine on Apr 18, 2017.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // for Fabric
        Fabric.with(this, Crashlytics())

        // for Realm
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}
