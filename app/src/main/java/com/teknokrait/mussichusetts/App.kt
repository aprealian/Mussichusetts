package com.teknokrait.mussichusetts

import android.app.Application
import com.facebook.stetho.Stetho
import com.teknokrait.mussichusetts.di.component.DaggerAppComponent
import com.teknokrait.mussichusetts.di.module.AppModule
import com.teknokrait.mussichusetts.di.module.NetModule
import dagger.android.support.DaggerAppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)


        //The way you build your top-level Application component can vary. This is just an example
//        DaggerAppComponent.builder()
//                .build()
//                .inject(this);

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(BuildConfig.URL))
                .build()
                .inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}