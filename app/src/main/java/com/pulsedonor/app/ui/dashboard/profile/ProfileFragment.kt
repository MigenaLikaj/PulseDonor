package com.pulsedonor.app.ui.dashboard.profile

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.databinding.ProfileFragmentBinding
import com.pulsedonor.app.ui.oboarding.intro.IntroductionActivity

class ProfileFragment : BaseFragment<ProfileFragmentBinding>() {

    override fun inflateBinding(layoutInflater: LayoutInflater): ProfileFragmentBinding =
        ProfileFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
    }

    override fun initViews() {
    }

    override fun onClicks() {
        binding.clLogOut.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext()).setItems(
                arrayOf("A jeni i/e sigurt qe deshironi te dilni?")
            ) { _, which -> }
            alert.setCancelable(true).setPositiveButton("Po") { dialog, _ ->
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
    }
}