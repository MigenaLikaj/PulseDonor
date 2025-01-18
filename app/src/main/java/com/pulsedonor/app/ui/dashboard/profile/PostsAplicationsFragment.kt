package com.pulsedonor.app.ui.dashboard.profile

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.profile.ApplicantsAdapter
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.PostsAplicationsFragmentBinding
import com.pulsedonor.app.models.profile.PostApplicationsData
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class PostsApplicationsFragment(var postId: Int) : BaseFragment<PostsAplicationsFragmentBinding>(),
    ApplicantsAdapter.Listener {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    lateinit var applicantsAdapter: ApplicantsAdapter
    private var applicantsList = ArrayList<PostApplicationsData?>()

    override fun inflateBinding(layoutInflater: LayoutInflater): PostsAplicationsFragmentBinding =
        PostsAplicationsFragmentBinding.inflate(layoutInflater)


    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getBloodReqApplications(postId)

        applicantsAdapter = ApplicantsAdapter(this)
        binding.rvAplicants.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = applicantsAdapter
        }

    }

    override fun onClicks() {
    }

    override fun observeViewModel() {

        viewModel.getBloodReqApplications.observe(this) {
            it.data?.let {
                applicantsList.clear()
                applicantsList.addAll(it)
                applicantsAdapter.submitList(applicantsList)
                applicantsAdapter.notifyDataSetChanged()
            }
            if (applicantsList.isEmpty()) {
                binding.tvDonationsListEmpty.visibility = View.VISIBLE
            } else {
                binding.tvDonationsListEmpty.visibility = View.GONE
            }
        }

        viewModel.confirmApplication.observe(this) {
            viewModel.getBloodReqApplications(postId)
        }
    }

    override fun onApplicantClicked(phoneNumber: String) {
        val alert = AlertDialog.Builder(requireContext()).setItems(
            arrayOf("A deshironi te kontaktoni kete person?")
        ) { _, which -> }
        alert.setCancelable(true).setPositiveButton("Po") { dialog, _ ->
            try {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Unable to open dialer", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }.setNegativeButton("Jo") { dialog, _ ->
            dialog.dismiss()
        }
        alert.setOnCancelListener { it.dismiss() }
        alert.create()
        alert.show()
    }

    override fun onConfirmClicked(item: PostApplicationsData) {
        val alert = AlertDialog.Builder(requireContext()).setItems(
            arrayOf("A deshironi ta pranoni kete person?")
        ) { _, which -> }
        alert.setCancelable(true).setPositiveButton("Po") { dialog, _ ->
            item.id?.let { viewModel.confirmApplication(it) }
            dialog.dismiss()
        }.setNegativeButton("Jo") { dialog, _ ->
            dialog.dismiss()
        }
        alert.setOnCancelListener { it.dismiss() }
        alert.create()
        alert.show()
    }

}