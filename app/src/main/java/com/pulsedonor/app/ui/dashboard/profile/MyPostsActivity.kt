package com.pulsedonor.app.ui.dashboard.profile

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.profile.MyPostsAdapter
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.MyPostsActivityBinding
import com.pulsedonor.app.models.profile.DonationsResponse
import com.pulsedonor.app.models.profile.MyPostData
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class MyPostsActivity : BaseActivity<MyPostsActivityBinding>(), MyPostsAdapter.Listener {
    override fun inflateBinding(layoutInflater: LayoutInflater): MyPostsActivityBinding =
        MyPostsActivityBinding.inflate(layoutInflater)

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel
    lateinit var myPostsAdapter: MyPostsAdapter
    private var donationsList = ArrayList<DonationsResponse?>()
    private var myPostsList = ArrayList<MyPostData?>()

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        myPostsAdapter = MyPostsAdapter(this)
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = myPostsAdapter
        }

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
//        myPostsAdapter.submitList(donationsList)
    }

    override fun observeViewModel() {

        viewModel.getUserProfileBloodRequests.observe(this) { response ->
            response.data?.let { data ->
                myPostsList.clear()
                myPostsList.addAll(data)
                myPostsAdapter.submitList(myPostsList)
                myPostsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.ivAddPost.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }
    }

    override fun onPostClicked(item: MyPostData) {
        val intent = Intent(this, PostDetailsActivity::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserProfileBloodRequests()
    }
}