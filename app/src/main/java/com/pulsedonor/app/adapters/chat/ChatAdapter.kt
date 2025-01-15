package com.pulsedonor.app.adapters.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.databinding.ItemChatReceivedBinding
import com.pulsedonor.app.databinding.ItemChatSentBinding
import com.pulsedonor.app.models.home.ChatsMessagesData

class ChatAdapter : ListAdapter<ChatsMessagesData, RecyclerView.ViewHolder>(DiffCallback()) {

    private val VIEW_TYPE_MESSAGE_SENT = 1
    private val VIEW_TYPE_MESSAGE_RECEIVED = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            val binding =
                ItemChatSentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SentMessageItemViewHolder(binding)
        } else {
            val binding =
                ItemChatReceivedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ReceiverMessageItemViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isSender) VIEW_TYPE_MESSAGE_SENT else VIEW_TYPE_MESSAGE_RECEIVED
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = getItem(position)
        if (holder is SentMessageItemViewHolder) {
            holder.bind(message)
        } else if (holder is ReceiverMessageItemViewHolder) {
            holder.bind(message)
        }
    }

    inner class SentMessageItemViewHolder(private val binding: ItemChatSentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatsMessagesData) {
            binding.tvMsgSent.text = message.message
        }
    }

    inner class ReceiverMessageItemViewHolder(private val binding: ItemChatReceivedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatsMessagesData) {
            binding.tvMsgReceived.text = message.message
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ChatsMessagesData>() {
        override fun areItemsTheSame(
            oldItem: ChatsMessagesData,
            newItem: ChatsMessagesData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ChatsMessagesData,
            newItem: ChatsMessagesData
        ): Boolean {
            return oldItem == newItem
        }
    }
}
