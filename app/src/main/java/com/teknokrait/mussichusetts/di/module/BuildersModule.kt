package com.teknokrait.mussichusetts.di.module

import com.teknokrait.mussichusetts.view.ui.activity.MainActivity
import com.teknokrait.mussichusetts.view.ui.fragment.FindFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Aprilian Nur Wakhid Daini on 7/18/2019.
 */
@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeTracksFindFragment(): FindFragment
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}