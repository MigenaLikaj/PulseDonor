package com.pulsedonor.app.adapters.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.R
import com.pulsedonor.app.databinding.PostInHomeItemBinding
import com.pulsedonor.app.models.home.HomePostsResponse
import com.pulsedonor.app.utilities.loadUrl
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class PostsInHomeAdapter(val listener: Listener) :
    ListAdapter<HomePostsResponse, RecyclerView.ViewHolder>(DiffCallback()) {

    init {
        hasStableIds()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = PostInHomeItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PostsInHomeItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostsInHomeItemViewHolder).bind(getItem(position) as HomePostsResponse)
    }

    inner class PostsInHomeItemViewHolder(val binding: PostInHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: HomePostsResponse) = with(itemView) {

            binding.tvPosterName.text = item.posterName
            binding.tvUrgency.text = item.urgency
            binding.tvHospital.text = item.hospital
            binding.tvQuantity.text = item.quantity
            binding.tvBloodRecipientAge.text = item.bloodRecipientAge.toString()
            binding.tvBloodRecipientName.text = item.bloodRecipientName
            binding.civPosterImage.loadUrl(
                item.posterImage, R.drawable.empty_profile, null
            )

            val utcDateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }
            val date = utcDateFormat.parse(item.dateTime)
            val localDateFormat = SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault())
            val formattedDayStr = localDateFormat.format(date)
            binding.tvDateTime.text = formattedDayStr

            binding.btnApply.setOnClickListener {
                listener.onApplyClicked()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<HomePostsResponse>() {
        override fun areItemsTheSame(
            oldItem: HomePostsResponse, newItem: HomePostsResponse
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: HomePostsResponse, newItem: HomePostsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onApplyClicked()
    }
}