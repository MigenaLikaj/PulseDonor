package com.pulsedonor.app.ui.oboarding.signin

import android.view.LayoutInflater
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.SignInActivityBinding
import com.pulsedonor.app.ui.dashboard.DashboardActivity
import com.pulsedonor.app.ui.oboarding.forgot_password.ForgotPasswordActivity
import com.pulsedonor.app.ui.oboarding.signup.SignUpActivity
import com.pulsedonor.app.utilities.openActivity
import javax.inject.Inject

class SignInActivity : BaseActivity<SignInActivityBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun inflateBinding(layoutInflater: LayoutInflater): SignInActivityBinding =
        SignInActivityBinding.inflate(layoutInflater)

    override fun initViews() {

    }

    override fun observeViewModel() {
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvSignUp.setOnClickListener {
            openActivity(this, SignUpActivity())
        }

        binding.tvForgotPassword.setOnClickListener {
            openActivity(this, ForgotPasswordActivity())
        }

        binding.btnSignIn.setOnClickListener {
            openActivity(this, DashboardActivity())
        }
    }
}