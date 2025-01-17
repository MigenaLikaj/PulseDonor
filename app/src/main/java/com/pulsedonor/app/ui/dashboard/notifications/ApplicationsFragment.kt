package com.pulsedonor.app.ui.dashboard.notifications

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.ApplicationsFragmentBinding
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject


class ApplicationsFragment : BaseFragment<ApplicationsFragmentBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun inflateBinding(layoutInflater: LayoutInflater): ApplicationsFragmentBinding =
        ApplicationsFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun initViews() {
    }

    override fun onClicks() {
    }

}