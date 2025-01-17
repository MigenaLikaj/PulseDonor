package com.pulsedonor.app.ui.dashboard.profile

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.profile.ApplicantsAdapter
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.PostsAplicationsFragmentBinding
import com.pulsedonor.app.models.profile.ApplicantsResponse
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class PostsApplicationsFragment(postId: Int) : BaseFragment<PostsAplicationsFragmentBinding>(),
    ApplicantsAdapter.Listener {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    lateinit var applicantsAdapter: ApplicantsAdapter
    private var applicantsList = ArrayList<ApplicantsResponse?>()

    override fun inflateBinding(layoutInflater: LayoutInflater): PostsAplicationsFragmentBinding =
        PostsAplicationsFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
    }

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        applicantsAdapter = ApplicantsAdapter(this)
        binding.rvAplicants.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = applicantsAdapter
        }

        applicantsList = arrayListOf(
            ApplicantsResponse(
                fullName = "John Doe",
                bloodGroup = "O+",
                phoneNumber = "+1234567890",
                email = "johndoe@example.com"
            ),
            ApplicantsResponse(
                fullName = "Jane Smith",
                bloodGroup = "A-",
                phoneNumber = "+1987654321",
                email = "janesmith@example.com"
            ),
            ApplicantsResponse(
                fullName = "Michael Brown",
                bloodGroup = "B+",
                phoneNumber = "+1123456789",
                email = "michaelbrown@example.com"
            ),
            ApplicantsResponse(
                fullName = "Emily Davis",
                bloodGroup = "AB-",
                phoneNumber = "+1098765432",
                email = "emilydavis@example.com"
            ),
            ApplicantsResponse(
                fullName = "Chris Johnson",
                bloodGroup = "O-",
                phoneNumber = "+1245789630",
                email = "chrisjohnson@example.com"
            ),
            ApplicantsResponse(
                fullName = "Patricia Wilson",
                bloodGroup = "A+",
                phoneNumber = "+1345678902",
                email = "patriciawilson@example.com"
            ),
            ApplicantsResponse(
                fullName = "David Martinez",
                bloodGroup = "B-",
                phoneNumber = "+1456789012",
                email = "davidmartinez@example.com"
            ),
            ApplicantsResponse(
                fullName = "Sophia Garcia",
                bloodGroup = "AB+",
                phoneNumber = "+1567890123",
                email = "sophiagarcia@example.com"
            )
        )

        applicantsAdapter.submitList(applicantsList)
    }

    override fun onClicks() {
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

}