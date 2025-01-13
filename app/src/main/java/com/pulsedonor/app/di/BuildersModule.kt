package com.pulsedonor.app.di

import com.pulsedonor.app.di.modules.DashboardActivityModule
import com.pulsedonor.app.di.modules.ProfileActivityModule
import com.pulsedonor.app.ui.dashboard.DashboardActivity
import com.pulsedonor.app.ui.dashboard.profile.ChangePasswordActivity
import com.pulsedonor.app.ui.dashboard.profile.DonationsActivity
import com.pulsedonor.app.ui.dashboard.profile.MyPostsActivity
import com.pulsedonor.app.ui.dashboard.profile.PostDetailsActivity
import com.pulsedonor.app.ui.dashboard.profile.ProfileActivity
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

    @ContributesAndroidInjector(modules = [ProfileActivityModule::class])
    internal abstract fun bindPostDetailsActivity(): PostDetailsActivity

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

    @ContributesAndroidInjector()
    internal abstract fun bindDonationsActivity(): DonationsActivity

    @ContributesAndroidInjector()
    internal abstract fun bindMyPostsActivity(): MyPostsActivity

    @ContributesAndroidInjector()
    internal abstract fun bindChangePasswordActivity(): ChangePasswordActivity

    @ContributesAndroidInjector()
    internal abstract fun bindProfileActivity(): ProfileActivity
}