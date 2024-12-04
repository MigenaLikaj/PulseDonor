package com.pulsedonor.app.di

import android.app.Activity
import android.app.Application
import android.app.Service
import androidx.fragment.app.Fragment
import com.pulsedonor.app.data.AppPreferences
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasSupportFragmentInjector,
    HasServiceInjector {


    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate() {
        super.onCreate()


        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

//        baseAccountManager.start()

    }


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    override fun activityInjector() = activityInjector
    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector
    override fun serviceInjector() = serviceInjector
//
//    fun setConnectivityListener(listener: ConnectionLiveData.ConnectivityReceiverListener) {
//        ConnectionLiveData.connectivityReceiverListener = listener
//    }
}