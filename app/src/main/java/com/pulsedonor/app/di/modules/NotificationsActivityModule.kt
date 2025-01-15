package com.pulsedonor.app.di.modules

import com.pulsedonor.app.ui.dashboard.notifications.ApplicationsFragment
import com.pulsedonor.app.ui.dashboard.notifications.RequestsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class NotificationsActivityModule {
    @ContributesAndroidInjector
    internal abstract fun bindRequestsFragment(): RequestsFragment

    @ContributesAndroidInjector
    internal abstract fun bindApplicationsFragment(): ApplicationsFragment
}