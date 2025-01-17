package com.pulsedonor.app.ui.dashboard.chat

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pulsedonor.app.adapters.chat.ChatAdapter
import com.pulsedonor.app.base.activity.BaseActivity
import com.pulsedonor.app.base.viewmodel.PulseDonorViewModelFactory
import com.pulsedonor.app.data.AppPreferences
import com.pulsedonor.app.databinding.ChatActivityBinding
import com.pulsedonor.app.models.home.ChatsMessagesData
import com.pulsedonor.app.ui.MainViewModel
import javax.inject.Inject

class ChatActivity : BaseActivity<ChatActivityBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: PulseDonorViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var chatAdapter: ChatAdapter
    private val messagesList = mutableListOf<ChatsMessagesData>()

    override fun inflateBinding(layoutInflater: LayoutInflater): ChatActivityBinding =
        ChatActivityBinding.inflate(layoutInflater)

    override fun initViews() {
        chatAdapter = ChatAdapter()
        binding.rvMessages.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = chatAdapter
        }
    }

    override fun observeViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

    }

    override fun onClicks() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.cvSendMessage.setOnClickListener {
            val messageText = binding.etMessageToSent.text.toString().trim()
            if (messageText.isNotEmpty()) {
                addMessageToList(messageText)
                binding.etMessageToSent.text?.clear()
            }
        }
    }

    private fun addMessageToList(message: String) {
        val senderMessage = ChatsMessagesData(message, isSender = true)
        val receiverMessage = ChatsMessagesData(message, isSender = false)

        messagesList.add(senderMessage)
        messagesList.add(receiverMessage)

        chatAdapter.submitList(messagesList.toList())
        chatAdapter.notifyDataSetChanged()
        binding.rvMessages.post {
            binding.rvMessages.scrollToPosition(messagesList.size - 1)
        }
        println("Messages count: ${messagesList.size}")
        println("Last message: ${messagesList.lastOrNull()?.message}")
    }
}
