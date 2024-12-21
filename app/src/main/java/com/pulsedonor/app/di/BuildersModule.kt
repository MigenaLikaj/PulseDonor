package com.pulsedonor.app.di

import com.pulsedonor.app.di.modules.DashboardActivityModule
import com.pulsedonor.app.ui.dashboard.DashboardActivity
import com.pulsedonor.app.ui.oboarding.forgot_password.ForgotPasswordActivity
import com.pulsedonor.app.ui.oboarding.intro.IntroductionActivity
import com.pulsedonor.app.ui.oboarding.signin.SignInActivity
import com.pulsedonor.app.ui.oboarding.signup.SignUpActivity
import com.pulsedonor.app.ui.oboarding.splash_screen.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [DashboardActivityModule::class])
    internal abstract fun bindDashboardActivity(): DashboardActivity

    @ContributesAndroidInjector()
    internal abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector()
    internal abstract fun bindSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector()
    internal abstract fun bindSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector()
    internal abstract fun bindIntroductionActivity(): IntroductionActivity

    @ContributesAndroidInjector()
    internal abstract fun bindForgotPasswordActivity(): ForgotPasswordActivity
}