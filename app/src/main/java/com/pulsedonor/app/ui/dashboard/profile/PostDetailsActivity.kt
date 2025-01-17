package com.pulsedonor.app.ui.dashboard.profile

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.PostDetailsActivityBinding
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class PostDetailsActivity : BaseActivity<PostDetailsActivityBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
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

        binding.vpRanking.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                selectedTab = position
            }
        })
    }

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun observeViewModel() {
    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}