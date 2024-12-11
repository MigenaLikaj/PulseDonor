package com.pulsedonor.app.ui.oboarding.intro

import com.pulsedonor.app.R
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pulsedonor.app.adapters.introduction.IntroductionViewPager2Adapter
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.IntroductionActivityBinding
import com.pulsedonor.app.models.introduction.IntroductionModel
import com.pulsedonor.app.ui.oboarding.signin.SignInActivity
import javax.inject.Inject

class IntroductionActivity : BaseActivity<IntroductionActivityBinding>() {

    lateinit var introductionAdapter: IntroductionViewPager2Adapter

    @Inject
    lateinit var appPreferences: AppPreferences

    override fun inflateBinding(layoutInflater: LayoutInflater): IntroductionActivityBinding =
        IntroductionActivityBinding.inflate(layoutInflater)

    override fun initViews() {
        introductionAdapter = IntroductionViewPager2Adapter(this)
        binding.viewPager2.adapter = introductionAdapter
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager2.isUserInputEnabled = true
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.contentDescription = "${position + 1}"
            println("position intro $position")
        }.attach()
        fillIntroData()
        setUpViewPager()
    }

    private fun fillIntroData() {
        val list = arrayListOf(
            IntroductionModel(
                R.drawable.intro1
            ),
            IntroductionModel(
                R.drawable.intro2

            ),
            IntroductionModel(
                R.drawable.intro3
            )
        )
        introductionAdapter.submitList(list)
    }

    private fun setUpViewPager() {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(position: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })
    }

    private fun openActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

    override fun observeViewModel() {}

    override fun onClicks() {
        binding.btnLogin.setOnClickListener {
            openActivity(SignInActivity())
        }

        binding.btnRegister.setOnClickListener {
//            openActivity(SignUpActivity())
        }
    }
}