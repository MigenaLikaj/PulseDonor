package com.pulsedonor.app.adapters.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.databinding.ApplicantsItemBinding
import com.pulsedonor.app.models.profile.ApplicantsResponse

class ApplicantsAdapter(val listener: Listener) :
    ListAdapter<ApplicantsResponse, RecyclerView.ViewHolder>(DiffCallback()) {

    init {
        hasStableIds()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ApplicantsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ApplicantionsItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ApplicantionsItemViewHolder).bind(getItem(position) as ApplicantsResponse)
    }

    inner class ApplicantionsItemViewHolder(val binding: ApplicantsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: ApplicantsResponse) = with(itemView) {

            binding.tvFullName.text = item.fullName
            binding.tvEmail.text = item.email
            binding.tvPhoneNumber.text = item.phoneNumber
            binding.tvBloodGroup.text = item.bloodGroup

            binding.root.setOnClickListener {
                listener.onApplicantClicked(item.phoneNumber)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ApplicantsResponse>() {
        override fun areItemsTheSame(
            oldItem: ApplicantsResponse, newItem: ApplicantsResponse
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: ApplicantsResponse, newItem: ApplicantsResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onApplicantClicked(phoneNumber: String)
    }
}