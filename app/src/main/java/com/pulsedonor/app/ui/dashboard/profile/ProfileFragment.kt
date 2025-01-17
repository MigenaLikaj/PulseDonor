package com.pulsedonor.app.ui.dashboard.profile

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.ProfileFragmentBinding
import com.pulsedonor.app.ui.MainViewModel
import com.pulsedonor.app.ui.oboarding.intro.IntroductionActivity
import javax.inject.Inject

class ProfileFragment : BaseFragment<ProfileFragmentBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory

    override fun inflateBinding(layoutInflater: LayoutInflater): ProfileFragmentBinding =
        ProfileFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
    }

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onClicks() {
        binding.clLogOut.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext()).setItems(
                arrayOf("A jeni i/e sigurt qe deshironi te dilni?")
            ) { _, which -> }
            alert.setCancelable(true).setPositiveButton("Po") { dialog, _ ->
                appPreferences.token = null
                startActivity(Intent(requireContext(), IntroductionActivity::class.java))
                requireActivity().finish()
            }.setNegativeButton("Jo") { dialog, _ ->
                dialog.dismiss()
            }
            alert.setOnCancelListener { it.dismiss() }
            alert.create()
            alert.show()
        }

        binding.clOverview.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        binding.clListOfDonations.setOnClickListener {
            startActivity(Intent(requireContext(), DonationsActivity::class.java))
        }

        binding.clMyPosts.setOnClickListener {
            startActivity(Intent(requireContext(), MyPostsActivity::class.java))
        }

        binding.clChangePassword.setOnClickListener {
            startActivity(Intent(requireContext(), ChangePasswordActivity::class.java))
        }
    }
}