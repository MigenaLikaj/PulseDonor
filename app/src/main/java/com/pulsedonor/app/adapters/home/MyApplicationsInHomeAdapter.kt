package com.pulsedonor.app.adapters.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.R
import com.pulsedonor.app.databinding.ApplicationsListItemBinding
import com.pulsedonor.app.models.home.HomeApplicationsResponse
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class MyApplicationsInHomeAdapter :
    ListAdapter<HomeApplicationsResponse, RecyclerView.ViewHolder>(DiffCallback()) {

    init {
        hasStableIds()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ApplicationsListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ApplicationsInHomeItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ApplicationsInHomeItemViewHolder).bind(getItem(position) as HomeApplicationsResponse)
    }

    inner class ApplicationsInHomeItemViewHolder(val binding: ApplicationsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: HomeApplicationsResponse) = with(itemView) {

            binding.tvUrgency.text = item.urgency
            binding.tvQuantity.text = item.quantity
            binding.tvBloodGroup.text = item.bloodGroup

            val utcDateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }
            val date = utcDateFormat.parse(item.dateTime)
            val localDateFormat = SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault())
            val formattedDayStr = localDateFormat.format(date)
            binding.tvDateTime.text = formattedDayStr

            if (item.statustype == 1) {
                binding.ivStatus.setImageResource(R.drawable.accept)
            } else if (item.statustype == 2) {
                binding.ivStatus.setImageResource(R.drawable.reject)
            } else {
                binding.ivStatus.setImageResource(R.drawable.clockwise)
            }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<HomeApplicationsResponse>() {
        override fun areItemsTheSame(
            oldItem: HomeApplicationsResponse, newItem: HomeApplicationsResponse
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: HomeApplicationsResponse, newItem: HomeApplicationsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

}