package com.pulsedonor.app.di.modules

import com.pulsedonor.app.ui.dashboard.hall_of_fame.HallOfFameFragment
import com.pulsedonor.app.ui.dashboard.home.HomeApplicationsFragment
import com.pulsedonor.app.ui.dashboard.home.HomeFragment
import com.pulsedonor.app.ui.dashboard.home.HomePostsFragment
import com.pulsedonor.app.ui.dashboard.map.MapFragment
import com.pulsedonor.app.ui.dashboard.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class DashboardActivityModule {
    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun bindHallOfFameFragment(): HallOfFameFragment

    @ContributesAndroidInjector
    internal abstract fun bindMapFragment(): MapFragment

    @ContributesAndroidInjector
    internal abstract fun bindProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun bindHomePostsFragment(): HomePostsFragment

    @ContributesAndroidInjector
    internal abstract fun bindHomeAplicationsFragment(): HomeApplicationsFragment
}