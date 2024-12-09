package com.pulsedonor.app.di

import com.pulsedonor.app.di.modules.MainActivityModule
import com.pulsedonor.app.ui.MainActivity
import com.pulsedonor.app.ui.oboarding.intro.IntroductionActivity
import com.pulsedonor.app.ui.oboarding.signin.SignInActivity
import com.pulsedonor.app.ui.oboarding.signup.SignUpActivity
import com.pulsedonor.app.ui.oboarding.splash_screen.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector()
    internal abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector()
    internal abstract fun bindSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector()
    internal abstract fun bindSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector()
    internal abstract fun bindIntroductionActivity(): IntroductionActivity

}