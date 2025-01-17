package com.pulsedonor.app.ui.oboarding.signin

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.SignInActivityBinding
import com.pulsedonor.app.events.ShowToastEvent
import com.pulsedonor.app.ui.MainViewModel
import com.pulsedonor.app.ui.dashboard.DashboardActivity
import com.pulsedonor.app.ui.oboarding.forgot_password.ForgotPasswordActivity
import com.pulsedonor.app.ui.oboarding.signup.SignUpActivity
import com.pulsedonor.app.utilities.isEmailValid
import com.pulsedonor.app.utilities.openActivity
import com.pulsedonor.app.utilities.showToast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class SignInActivity : BaseActivity<SignInActivityBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    var email = ""
    var password = ""

    override fun inflateBinding(layoutInflater: LayoutInflater): SignInActivityBinding =
        SignInActivityBinding.inflate(layoutInflater)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun observeViewModel() {
        viewModel.successSignIn.observe(this) {
            if (it == true) {
                Toast.makeText(this, "Ju jeni kyçur me sukses!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
            }
        }
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
//            openActivity(this, DashboardActivity())
            email = binding.etEmail.editableText.toString()
            password = binding.etPassword.editableText.toString()
            if (isEmailValid(email)) {
                appPreferences.email = email
                viewModel.signIn(email, password)
            } else {
                binding.etEmail.error = "Email is not valid!"

            }
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe
    fun event(showToastEvent: ShowToastEvent) {
        showToast(showToastEvent.message)
    }
}