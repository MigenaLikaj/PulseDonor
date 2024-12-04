package com.pulsedonor.app.di.modules

import com.pulsedonor.app.ui.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    internal abstract fun bindWelcomeFragment(): WelcomeFragment
}