package com.pulsedonor.app.adapters.hall_of_fame

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.R
import com.pulsedonor.app.databinding.DonorItemBinding
import com.pulsedonor.app.models.hall_of_fame.DonorsResponse
import com.pulsedonor.app.utilities.loadUrl

class DonorsAdapter : ListAdapter<DonorsResponse, RecyclerView.ViewHolder>(DiffCallback()) {

    init {
        hasStableIds()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DonorItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DonorsItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DonorsItemViewHolder).bind(getItem(position) as DonorsResponse)
    }

    inner class DonorsItemViewHolder(val binding: DonorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: DonorsResponse) = with(itemView) {

            binding.tvDonorFullName.text = item.fullName
            binding.civDonorImage.loadUrl(
                item.image, R.drawable.empty_profile, null
            )

            when (item.placeOnTopList) {
                3 -> {
                    binding.ivMedal.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.root.context,
                            R.drawable.medal3
                        )
                    )
                }

                2 -> {
                    binding.ivMedal.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.root.context,
                            R.drawable.medal2
                        )
                    )
                }

                1 -> {
                    binding.ivMedal.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.root.context,
                            R.drawable.medal1
                        )
                    )
                }

                else -> {
                    binding.ivMedal.visibility = View.GONE
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DonorsResponse>() {
        override fun areItemsTheSame(
            oldItem: DonorsResponse, newItem: DonorsResponse
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: DonorsResponse, newItem: DonorsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}