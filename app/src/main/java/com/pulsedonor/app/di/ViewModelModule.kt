package com.pulsedonor.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.di.annotation.ViewModelKey
import com.pulsedonor.app.ui.MainViewModel
import com.pulsedonor.app.ui.WelcomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module(includes = [FragmentsViewModelModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    abstract fun bindWelcomeViewModel(welcomeViewModel: WelcomeViewModel): ViewModel
//    /**baseFactory for all [ViewModel]*/
    @Binds
    abstract fun bindViewModelFactory(factory: PulseDonorViewModelFactory): ViewModelProvider.Factory

}