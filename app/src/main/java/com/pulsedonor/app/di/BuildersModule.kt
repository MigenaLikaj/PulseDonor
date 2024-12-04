package com.pulsedonor.app.di

import com.pulsedonor.app.di.modules.MainActivityModule
import com.pulsedonor.app.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity() : MainActivity

//    @ContributesAndroidInjector(modules = [OnBoardingActivityModule::class])
//    internal abstract fun bindOnBoardingActivity() : OnBoardingActivity

}