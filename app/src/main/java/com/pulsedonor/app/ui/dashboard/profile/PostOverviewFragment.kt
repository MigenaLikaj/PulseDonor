package com.pulsedonor.app.ui.dashboard.profile

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.PostOverviewFragmentBinding
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class PostsOverviewFragment : BaseFragment<PostOverviewFragmentBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun inflateBinding(layoutInflater: LayoutInflater): PostOverviewFragmentBinding =
        PostOverviewFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
    }

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onClicks() {
    }

}