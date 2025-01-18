package com.pulsedonor.app.adapters.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.R
import com.pulsedonor.app.databinding.ApplicationsListItemBinding
import com.pulsedonor.app.models.datas.ApplicationData

class MyApplicationsInHomeAdapter :
    ListAdapter<ApplicationData, RecyclerView.ViewHolder>(DiffCallback()) {

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
        (holder as ApplicationsInHomeItemViewHolder).bind(getItem(position) as ApplicationData)
    }

    inner class ApplicationsInHomeItemViewHolder(val binding: ApplicationsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: ApplicationData) = with(itemView) {

            binding.tvUrgency.text = item.urgence?.urgenceType
            binding.tvQuantity.text = item.quantity.toString()
            binding.tvBloodGroup.text = item.bloodType?.bloodType

            binding.tvDateTime.text = "${item.donationDate} ${item.donationTime}"

            if (item.isAccepted == true) {
                binding.ivStatus.setImageResource(R.drawable.accept)
            } else {
                binding.ivStatus.setImageResource(R.drawable.reject)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ApplicationData>() {
        override fun areItemsTheSame(
            oldItem: ApplicationData, newItem: ApplicationData
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: ApplicationData, newItem: ApplicationData
        ): Boolean {
            return oldItem == newItem
        }
    }

}