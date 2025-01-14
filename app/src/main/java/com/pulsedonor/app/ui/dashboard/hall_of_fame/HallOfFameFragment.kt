package com.pulsedonor.app.ui.dashboard.hall_of_fame

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.hall_of_fame.DonorsAdapter
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.databinding.HallOfFameFragmentBinding
import com.pulsedonor.app.models.hall_of_fame.DonorsResponse

class HallOfFameFragment : BaseFragment<HallOfFameFragmentBinding>() {

    lateinit var donorsAdapter: DonorsAdapter
    lateinit var topDonorsAdapter: DonorsAdapter
    private var donorsList = ArrayList<DonorsResponse?>()
    private var topDonorsList = ArrayList<DonorsResponse?>()

    override fun inflateBinding(layoutInflater: LayoutInflater): HallOfFameFragmentBinding =
        HallOfFameFragmentBinding.inflate(layoutInflater)


    override fun observeViewModel() {
    }

    override fun initViews() {
        // Initialize and set adapter for donors
        donorsAdapter = DonorsAdapter()
        binding.rvDonors.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = donorsAdapter
        }

        donorsList = arrayListOf(
            DonorsResponse("Alexa Bob", "https://randomuser.me/api/portraits/men/1.jpg", 0),
            DonorsResponse("Leo Brbe", "https://randomuser.me/api/portraits/men/3.jpg", 0),
            DonorsResponse("Siri Jcobs", "https://randomuser.me/api/portraits/women/4.jpg", 0),
        )
        donorsAdapter.submitList(donorsList)

        // Initialize and set adapter for top donors
        topDonorsAdapter = DonorsAdapter()
        binding.rvTopDonors.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = topDonorsAdapter
        }

        topDonorsList = arrayListOf(
            DonorsResponse("Arber Mirena", "https://randomuser.me/api/portraits/men/1.jpg", 1),
            DonorsResponse("Blend Hasani", "https://randomuser.me/api/portraits/men/3.jpg", 2),
            DonorsResponse("Migena Likaj", "https://randomuser.me/api/portraits/women/4.jpg", 3),
        )
        topDonorsAdapter.submitList(topDonorsList)
    }


    override fun onClicks() {
    }

}