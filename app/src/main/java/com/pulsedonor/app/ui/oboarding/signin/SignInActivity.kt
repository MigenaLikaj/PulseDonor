package com.pulsedonor.app.ui.oboarding.signin

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pulsedonor.app.R
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.SignInActivityBinding
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
        binding.tvBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}