package com.pulsedonor.app.adapters.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.databinding.PostsListItemBinding
import com.pulsedonor.app.models.profile.MyPostData

class MyPostsAdapter(val listener: Listener) :
    ListAdapter<MyPostData, RecyclerView.ViewHolder>(DiffCallback()) {

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
        (holder as PostsItemViewHolder).bind(getItem(position) as MyPostData)
    }

    inner class PostsItemViewHolder(val binding: PostsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: MyPostData) = with(itemView) {

            binding.tvBloodGroup.text = item.bloodType
            binding.tvQuantity.text = "${item.quantity} ml"
            binding.tvUrgency.text = item.urgenceType?.type
            binding.tvHospital.text = item.hospital?.name
            binding.tvDateTime.text = "${item.donationDate} ${item.donationTime}"
            binding.tvNrOfAplications.text = item.numberOfApplications.toString()

            binding.root.setOnClickListener {
                listener.onPostClicked(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MyPostData>() {
        override fun areItemsTheSame(
            oldItem: MyPostData, newItem: MyPostData
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MyPostData, newItem: MyPostData
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onPostClicked(item: MyPostData)
    }
}