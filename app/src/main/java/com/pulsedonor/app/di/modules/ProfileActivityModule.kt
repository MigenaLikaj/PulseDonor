package com.pulsedonor.app.di.modules

import com.pulsedonor.app.ui.dashboard.profile.PostsApplicationsFragment
import com.pulsedonor.app.ui.dashboard.profile.PostsOverviewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ProfileActivityModule {
    @ContributesAndroidInjector
    internal abstract fun bindPostsOverviewFragment(): PostsOverviewFragment

    @ContributesAndroidInjector
    internal abstract fun bindPostsApplicationsFragment(): PostsApplicationsFragment
}