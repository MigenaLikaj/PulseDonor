package com.pulsedonor.app.ui

import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.widget.Toast
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.AppNameViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.WelcomeFragmentBinding
import javax.inject.Inject

class WelcomeFragment : BaseFragment<WelcomeFragmentBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): WelcomeFragmentBinding = WelcomeFragmentBinding.inflate(layoutInflater)

    private lateinit var viewModel: WelcomeViewModel

    @Inject
    lateinit var appPreferences: AppPreferences
    @Inject
    lateinit var viewModelFactory: AppNameViewModelFactory
    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[WelcomeViewModel::class.java]

//        Toast.makeText(requireContext(), appPreferences.token, Toast.LENGTH_SHORT).show()
        viewModel.getUserSubscription()
    }


    override fun observeViewModel() {}

    override fun onClicks() {}



}