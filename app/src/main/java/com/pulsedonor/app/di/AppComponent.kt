package com.pulsedonor.app.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),(AndroidSupportInjectionModule::class), (AppModule::class), (BuildersModule::class), (NetworkModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}