package com.pulsedonor.app.ui.dashboard.home

import android.content.Intent
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pulsedonor.app.base.fragment.BaseFragment
import com.pulsedonor.app.databinding.HomeFragmentBinding
import com.pulsedonor.app.ui.dashboard.chat.ChatActivity
import com.pulsedonor.app.ui.dashboard.notifications.NotificationsActivity

class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    private lateinit var vpAdapter: ViewPagerHome

    override fun inflateBinding(layoutInflater: LayoutInflater): HomeFragmentBinding =
        HomeFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
    }

    override fun initViews() {
        initTabLayoutAndViewPager()
    }

    override fun onClicks() {
        binding.clChat.setOnClickListener {
            startActivity(Intent(requireContext(), ChatActivity::class.java))
        }

        binding.ivNotifications.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationsActivity::class.java))
        }
    }

    private fun initTabLayoutAndViewPager() {
        vpAdapter = ViewPagerHome(childFragmentManager, lifecycle)
        binding.vpRanking.adapter = vpAdapter
        TabLayoutMediator(
            binding.tabLayout,
            binding.vpRanking
        ) { currentTab, currentPosition ->
            currentTab.text = when (currentPosition) {
                0 -> {
                    "Postet"
                }

                1 -> {
                    "Aplikimet"
                }

                else -> ""
            }

        }.attach()

        binding.vpRanking.registerOnPageChangeCallback(onPageChanged)
    }

    var selectedTab = 1
    private val onPageChanged = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            selectedTab = position + 1
        }
    }

}