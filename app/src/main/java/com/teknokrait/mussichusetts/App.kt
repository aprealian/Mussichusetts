package com.teknokrait.mussichusetts

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.facebook.stetho.Stetho
import com.teknokrait.mussichusetts.di.component.DaggerAppComponent
import com.teknokrait.mussichusetts.di.module.AppModule
import com.teknokrait.mussichusetts.di.module.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */

class App : Application(), HasActivityInjector, HasSupportFragmentInjector{

//    @Inject
//    var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>? = null
//
//    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment>? {
//        return fragmentDispatchingAndroidInjector
//    }

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)

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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}