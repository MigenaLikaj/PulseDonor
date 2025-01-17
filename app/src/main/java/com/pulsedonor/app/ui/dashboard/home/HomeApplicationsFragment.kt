package com.pulsedonor.app.ui.dashboard.home

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.home.MyApplicationsInHomeAdapter
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.HomeAplicationsFragmentBinding
import com.pulsedonor.app.models.home.HomeApplicationsResponse
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class HomeApplicationsFragment : BaseFragment<HomeAplicationsFragmentBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    lateinit var myApplicationsInHomeAdapter: MyApplicationsInHomeAdapter
    private var applicationsList = ArrayList<HomeApplicationsResponse?>()


    override fun inflateBinding(layoutInflater: LayoutInflater): HomeAplicationsFragmentBinding =
        HomeAplicationsFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun initViews() {
        myApplicationsInHomeAdapter = MyApplicationsInHomeAdapter()
        binding.rvApplications.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = myApplicationsInHomeAdapter
        }

        applicationsList = arrayListOf(
            HomeApplicationsResponse(
                id = 1,
                bloodGroup = "A+",
                quantity = "2 units",
                urgency = "High",
                dateTime = "2025-01-14T10:30:00",
                statustype = 1,
            ),
            HomeApplicationsResponse(
                id = 2,
                bloodGroup = "O-",
                quantity = "1 unit",
                urgency = "Medium",
                dateTime = "2025-01-14T12:00:00",
                statustype = 2,
            ),
            HomeApplicationsResponse(
                id = 3,
                bloodGroup = "B+",
                quantity = "3 units",
                urgency = "Critical",
                dateTime = "2025-01-14T15:45:00",
                statustype = 3,
            )
        )
        myApplicationsInHomeAdapter.submitList(applicationsList)
    }

    override fun onClicks() {
    }
}