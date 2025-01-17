package com.pulsedonor.app.adapters.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.databinding.PostInHomeItemBinding
import com.pulsedonor.app.models.home.BloodRequestsData

class PostsInHomeAdapter(val listener: Listener) :
    ListAdapter<BloodRequestsData, RecyclerView.ViewHolder>(DiffCallback()) {

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
        (holder as PostsInHomeItemViewHolder).bind(getItem(position) as BloodRequestsData)
    }

    inner class PostsInHomeItemViewHolder(val binding: PostInHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: BloodRequestsData) = with(itemView) {

            binding.tvPosterName.text = item.author?.name
            binding.tvUrgency.text = item.urgenceType?.type
            binding.tvHospital.text = item.hospital?.name
            binding.tvQuantity.text = item.quantity.toString()
            binding.tvBloodGroup.text = item.bloodType?.type
            binding.tvBloodRecipientAge.text = item.age.toString()
            binding.tvBloodRecipientName.text = "${item.firstName} ${item.lastName}"

//
//            val utcDateFormat =
//                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).apply {
//                    timeZone = TimeZone.getTimeZone("UTC")
//                }
//            val date = utcDateFormat.parse(item.dateTime)
//            val localDateFormat = SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault())
//            val formattedDayStr = localDateFormat.format(date)
            binding.tvDateTime.text = "${item.donationDate} - ${item.donationTime}"

            binding.btnApply.setOnClickListener {
                listener.onApplyClicked()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BloodRequestsData>() {
        override fun areItemsTheSame(
            oldItem: BloodRequestsData, newItem: BloodRequestsData
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: BloodRequestsData, newItem: BloodRequestsData
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onApplyClicked()
    }
}