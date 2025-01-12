package com.pulsedonor.app.adapters.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.databinding.PostsListItemBinding
import com.pulsedonor.app.models.introduction.DonationsResponse

class MyPostsAdapter :
    ListAdapter<DonationsResponse, RecyclerView.ViewHolder>(DiffCallback()) {

    init {
        hasStableIds()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = PostsListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PostsItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostsItemViewHolder).bind(getItem(position) as DonationsResponse)
    }

    inner class PostsItemViewHolder(val binding: PostsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: DonationsResponse) = with(itemView) {

            binding.tvBloodGroup.text = item.bloodGroup
            binding.tvQuantity.text = item.quantity
            binding.tvUrgency.text = item.urgency
            binding.tvHospital.text = item.hospital
            binding.tvDateTime.text = item.dateTime
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DonationsResponse>() {
        override fun areItemsTheSame(
            oldItem: DonationsResponse, newItem: DonationsResponse
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: DonationsResponse, newItem: DonationsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}