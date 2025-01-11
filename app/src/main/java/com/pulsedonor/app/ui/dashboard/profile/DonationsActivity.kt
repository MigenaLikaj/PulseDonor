package com.pulsedonor.app.ui.dashboard.profile

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.profile.MyDonationsAdapter
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.DonationsActivityBinding
import com.pulsedonor.app.models.introduction.DonationsResponse
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class DonationsActivity : BaseActivity<DonationsActivityBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): DonationsActivityBinding =
        DonationsActivityBinding.inflate(layoutInflater)

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel
    lateinit var myDonationsAdapter: MyDonationsAdapter
    private var donationsList = ArrayList<DonationsResponse?>()

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun initViews() {
        // Initialize the ViewModel
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        // Set up the adapter
        myDonationsAdapter = MyDonationsAdapter()
        binding.rvDonations.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = myDonationsAdapter
        }

        // Populate the donations list with sample data
        donationsList = arrayListOf(
            DonationsResponse("O+", "500 ml", "High", "University Hospital", "10/01/2025 - 14:30"),
            DonationsResponse("A-", "350 ml", "Medium", "General Hospital", "11/01/2025 - 12:00"),
            DonationsResponse("B+", "400 ml", "Low", "City Hospital", "09/01/2025 - 10:45"),
            DonationsResponse("AB-", "300 ml", "High", "Specialist Clinic", "08/01/2025 - 16:00"),
            DonationsResponse("O-", "250 ml", "Urgent", "Community Center", "07/01/2025 - 11:15"),
            DonationsResponse("A+", "450 ml", "Medium", "Regional Hospital", "06/01/2025 - 13:30"),
            DonationsResponse("B-", "500 ml", "Low", "Health Center", "05/01/2025 - 15:00"),
            DonationsResponse("AB+", "350 ml", "High", "Downtown Clinic", "04/01/2025 - 09:00")
        )

        // Submit the list to the adapter
        myDonationsAdapter.submitList(donationsList)
    }

    override fun observeViewModel() {
        // Observe ViewModel LiveData or other reactive data here if needed
    }

    override fun onClicks() {
        // Handle back button click
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
