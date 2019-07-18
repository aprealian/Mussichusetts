package com.teknokrait.mussichusetts.di.component

import com.teknokrait.mussichusetts.App
import com.teknokrait.mussichusetts.di.module.AppModule
import com.teknokrait.mussichusetts.di.module.BuildersModule
import com.teknokrait.mussichusetts.di.module.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
@Singleton
@Component(
        modules = [(AndroidInjectionModule::class), (BuildersModule::class), (AppModule::class), (NetModule::class)]
)
interface AppComponent {
    fun inject(app: App)
}