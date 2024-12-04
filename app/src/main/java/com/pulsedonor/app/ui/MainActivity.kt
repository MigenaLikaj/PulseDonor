package com.pulsedonor.app.ui

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.AppNameViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: AppNameViewModelFactory

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }


    override fun observeViewModel() {}

    override fun onClicks() {}
}