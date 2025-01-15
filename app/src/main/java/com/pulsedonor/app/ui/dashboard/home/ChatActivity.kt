package com.pulsedonor.app.ui.dashboard.home

import android.view.LayoutInflater
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.databinding.ChatActivityBinding

class ChatActivity : BaseActivity<ChatActivityBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): ChatActivityBinding =
        ChatActivityBinding.inflate(layoutInflater)

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