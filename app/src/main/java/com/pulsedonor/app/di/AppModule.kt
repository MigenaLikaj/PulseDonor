package com.pulsedonor.app.di

import android.app.Application
import com.pulsedonor.app.data.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    //    @Provides
//    @Singleton
//    internal fun provideContext(application: Application): Context = application
//
//    @Singleton
//    @Provides
//    fun provideNetworkUtil(context: Context) : NetworkUtil = NetworkUtil(context)
//
//    @Singleton
//    @Provides
//    fun provideBaseAccountManager(context: Context) : BaseAccountManager = BaseAccountManager(context)
    @Provides
    @Singleton
    fun provideSharedPreference(app: Application): AppPreferences = AppPreferences(app)

}