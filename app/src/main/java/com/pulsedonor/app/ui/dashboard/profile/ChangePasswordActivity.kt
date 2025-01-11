package com.pulsedonor.app.ui.dashboard.profile

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.ChangePasswordActivityBinding
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class ChangePasswordActivity : BaseActivity<ChangePasswordActivityBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): ChangePasswordActivityBinding =
        ChangePasswordActivityBinding.inflate(layoutInflater)

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun observeViewModel() {
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}