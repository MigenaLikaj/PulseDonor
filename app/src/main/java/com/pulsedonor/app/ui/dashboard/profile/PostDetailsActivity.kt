package com.pulsedonor.app.ui.dashboard.profile

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.databinding.PostDetailsActivityBinding

class PostDetailsActivity : BaseActivity<PostDetailsActivityBinding>() {

    private lateinit var vpAdapter: ViewPagerPosts
    private var selectedTab = 0

    override fun inflateBinding(layoutInflater: LayoutInflater): PostDetailsActivityBinding =
        PostDetailsActivityBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTabLayoutAndViewPager()
    }

    private fun initTabLayoutAndViewPager() {
        vpAdapter = ViewPagerPosts(supportFragmentManager, lifecycle)
        binding.vpRanking.adapter = vpAdapter

        TabLayoutMediator(binding.tabLayout, binding.vpRanking) { tab, position ->
            tab.text = when (position) {
                0 -> "Detajet"
                1 -> "Aplikimet"
                else -> ""
            }
        }.attach()

//        // Disable swiping for ViewPager2
//        binding.vpRanking.isUserInputEnabled = false

        binding.vpRanking.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                selectedTab = position
            }
        })
    }

    override fun initViews() {
    }

    override fun observeViewModel() {
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}