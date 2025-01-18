package com.pulsedonor.app.adapters.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pulsedonor.app.databinding.ApplicantsItemBinding
import com.pulsedonor.app.models.profile.PostApplicationsData

class ApplicantsAdapter(val listener: Listener) :
    ListAdapter<PostApplicationsData, RecyclerView.ViewHolder>(DiffCallback()) {

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
        (holder as ApplicantionsItemViewHolder).bind(getItem(position) as PostApplicationsData)
    }

    inner class ApplicantionsItemViewHolder(val binding: ApplicantsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
        fun bind(item: PostApplicationsData) = with(itemView) {

            binding.tvFullName.text = item.fullname
            binding.tvEmail.text = item.email
            binding.tvPhoneNumber.text = item.phoneNumber
            binding.tvBloodGroup.text = item.bloodType

            if (item.canConfirm == true) {
                binding.tvConfirm.visibility = View.VISIBLE
            } else {
                binding.tvConfirm.visibility = View.GONE
            }

            if (item.isAccepted == true) {
                binding.ivStatus.visibility = View.VISIBLE
            } else {
                binding.ivStatus.visibility = View.GONE

            }

            binding.root.setOnClickListener {
                listener.onApplicantClicked(item.phoneNumber.toString())
            }

            binding.tvConfirm.setOnClickListener {
                listener.onConfirmClicked(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PostApplicationsData>() {
        override fun areItemsTheSame(
            oldItem: PostApplicationsData, newItem: PostApplicationsData
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: PostApplicationsData, newItem: PostApplicationsData
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onApplicantClicked(phoneNumber: String)
        fun onConfirmClicked(item: PostApplicationsData)
    }
}