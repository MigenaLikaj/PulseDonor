package com.pulsedonor.app.ui.dashboard.home

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.home.MyApplicationsInHomeAdapter
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.HomeAplicationsFragmentBinding
import com.pulsedonor.app.models.datas.ApplicationData
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class HomeApplicationsFragment : BaseFragment<HomeAplicationsFragmentBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    lateinit var myApplicationsInHomeAdapter: MyApplicationsInHomeAdapter
    private var applicationsList = ArrayList<ApplicationData?>()


    override fun inflateBinding(layoutInflater: LayoutInflater): HomeAplicationsFragmentBinding =
        HomeAplicationsFragmentBinding.inflate(layoutInflater)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getUserProfileApplicationsAC()

        myApplicationsInHomeAdapter = MyApplicationsInHomeAdapter()
        binding.rvApplications.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = myApplicationsInHomeAdapter
        }

    }

    override fun observeViewModel() {

        viewModel.getUserProfileApplicationsAC.observe(this) {
            it.data?.let {
                applicationsList.clear()
                applicationsList.addAll(it)
                myApplicationsInHomeAdapter.submitList(applicationsList)
            }
        }
    }


    override fun onClicks() {
    }
}